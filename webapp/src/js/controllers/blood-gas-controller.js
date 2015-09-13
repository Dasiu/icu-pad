angular.module('ICUPad.controllers.BloodGas', [])

    .controller('BloodGasController', ['$http', '$rootScope', '$scope', '$location', '$timeout',
        function ($http, $rootScope, $scope, $location, $timeout) {
            $scope.title = "blood gas!";
            $scope.chartMode = false;
            $scope.toggleChartMode = function () {
                $scope.chartMode = !$scope.chartMode
            }

            $scope.$on('selectedDayChanged', function(event) {
                console.log('dateChangedZZZ');

                var startDate = $rootScope.beginOf($rootScope.selectedDay);
                console.log(startDate);
                var endDate = $rootScope.endOf($rootScope.selectedDay);
                console.log(endDate);

                loadTestResults(startDate, endDate);
            });

            $scope.gridOptions = {
                //columnDefs: [
                //    { name: 'name' },
                //    { name: 'gender' },
                //    { name: 'company' },
                //    { name: 'widgets' },
                //    { name: 'cumulativeWidgets', field: 'widgets', cellTemplate: '' }
                //]
                enableColumnMenus: false,
                rowTemplate: 'grid-row.html',
                data: 'gridData'
            };

            loadTestResults();
            setListeners();
            function loadTestResults(startDate, endDate) {
                if (!startDate) {
                    startDate = $rootScope.beginOf($rootScope.selectedDay);
                    endDate = $rootScope.endOf($rootScope.selectedDay);
                }
                var startDateString = startDate.toISOString().slice(0, 19);
                var endDateString = endDate.toISOString().slice(0, 19);

                console.log(startDateString);
                console.log(endDateString);

                // todo delete
                //$rootScope.patient = {
                //    id: 666, hl7Id: "254895", pesel: "14212808853", name: "Filip",
                //    surname: "Rysztak", sex: "MALE", address: {}, stays: {id: 9},
                //    birthDate: "2014-01-28", activeStay: {id: 9}
                //};
                console.log("test");
                console.log($rootScope.patient);
                $http({
                    url: $rootScope.globalSettings.serverUrl + '/stay/' + 9 + '/test-panel-result', // todo
                    method: 'GET'
                })
                    .success(function (data) {
                        console.log(data);
                        prepareTestResults(data);
                        $scope.testResults = data;
                    })
                    .error(function () {
                        console.log('Failed to load test panel result');
                    });

                function prepareTestResults(testResults) {
                    console.log(new Date());

                    var testNamesInArrays = testResults.map(function (obj) {
                        return obj.tests.map(function (test) {
                            return test.name
                        })
                    });
                    var testNames = [].concat.apply([], testNamesInArrays).filter(function (value, index, self) {
                        return self.indexOf(value) === index;
                    });
                    $scope.testNames = testNames;
                    $scope.selectedTestNames = ['pO2', 'pCO2', 'pH'];
                    chartData();
                    generateChart();
                    gridData();
                    gridOptions();

                    function chartData() {
                        $scope.dates = testResults.map(function (testPanel) {
                            return testPanel.requestDate
                        });
                        $scope.chartData = testResults.map(function (testPanel) {
                            var obj = {requestDate: testPanel.requestDate};
                            testPanel.tests.forEach(function (test) {
                                obj[test.name] = test.value
                            });
                            return obj;
                        })
                        console.log($scope.chartData);
                    }

                    function generateChart() {
                        $scope.chart = c3.generate({
                            data: {
                                //x: 'x',
                                xFormat: '%Y-%m-%d %H:%M:%S',
                                json: $scope.chartData,
                                keys: {
                                    x: 'requestDate',
                                    value: $scope.selectedTestNames
                                }
                            },
                            axis: {
                                x: {
                                    type: 'timeseries',
                                    tick: {
                                        format: '%H:%M'
                                    }
                                }
                            }
                        });
                    }

                    function gridData() {
                        $scope.gridData = testNames.map(function (testName) {
                            var row = {
                                testName: testName
                            };
                            testResults.forEach(function (testPanel) {
                                var test = testPanel.tests.filter(function (test) {
                                    return test.name === testName
                                })[0]
                                var testValue = test ? test.value : null;
                                row[testPanel.requestDate] = test;
                            })
                            return row
                        });
                        columnDefsF()
                        console.log('x')
                        console.log($scope.gridData)

                        function columnDefsF() {
                            var testNamesColumn = {
                                name: 'Badanie',
                                field: 'testName',
                                width: '180'
                            }
                            var colmnDefs = [].concat([testNamesColumn], testResults.map(function (testPanel) {
                                var requestDate = new Date(testPanel.requestDate);
                                var columnDef = {
                                    name: testPanel.requestDate,
                                    displayName: requestDate.getHours() + ':' + requestDate.getMinutes(),
                                    field: '' + testPanel.requestDate + '.value',
                                    width: '70',
                                    //cellTemplate: '<div class="ui-grid-cell-contents" >{{grid, row}}</div>'
                                    cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
                                        var cellVal = grid.getCellValue(row,col)
                                        //console.log('row');
                                        //console.log(row);
                                        //console.log('colmn');
                                        //console.log(col);
                                        //console.log('celval');
                                        //console.log(cellVal);
                                        var test = row.entity[col.name];
                                        //console.log('test');
                                        //console.log(test);

                                        if (!test) return 'standard';
                                        
                                        if (test.abnormality === 'BELOW_LOW_NORM') {
                                            return 'below-norm';
                                        } else if (test.abnormality === 'ABOVE_HIGH_NORM') {
                                            return 'above-norm';
                                        } else {
                                            return 'standard';
                                        }
                                    }
                                };
                                return columnDef;
                            }));
                            console.log(colmnDefs);
                            $scope.columnDefs = colmnDefs;
                        }
                    }

                    function gridOptions() {
                        $scope.gridOptions.columnDefs = $scope.columnDefs;
                            //,
                            //rowTemplate: 'grid-row.html',
                            //data: 'gridData'
                        //};
                    }
                }
            }

            function setListeners() {
                //$scope.$watch('chartMode', function (newValue) {
                //    console.log('chaneg');
                //    if (!newValue) {
                //        $timeout(function () {
                //            if ($scope.gridOptions.$gridServices) {
                //                $scope.gridOptions.$gridServices.DomUtilityService.RebuildGrid(
                //                    $scope.gridOptions.$gridScope,
                //                    $scope.gridOptions.ngGrid);
                //            }
                //        })
                //    }
                //
                //}, true)
            }
        }]);
