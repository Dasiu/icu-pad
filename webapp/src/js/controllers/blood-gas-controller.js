angular.module('ICUPad.controllers.BloodGas', [])

    .controller('BloodGasController', ['$http', '$rootScope', '$scope', '$location', '$timeout',
        function ($http, $rootScope, $scope, $location, $timeout) {
            $scope.title = "blood gas!";
            console.log('test');
            console.log($scope.chartMode);
            $scope.chartMode = $rootScope.chartMode === undefined
            console.log('test');
            console.log($scope.chartMode);
            $scope.toggleChartMode = function () {
                $rootScope.chartMode = !$rootScope.chartMode
                $scope.chartMode = $rootScope.chartMode
                console.log('changed in click func');
                //$location.path("blood-gas-grid");
            }

            $scope.gridOptions = {
                //columnDefs: [
                //    { name: 'name' },
                //    { name: 'gender' },
                //    { name: 'company' },
                //    { name: 'widgets' },
                //    { name: 'cumulativeWidgets', field: 'widgets', cellTemplate: '' }
                //]
                rowTemplate: 'grid-row.html',
                data: 'gridData'
            };

            loadTestResults();
            setListeners();
            function loadTestResults() {
                // todo delete
                $rootScope.patient = {
                    id: 666, hl7Id: "254895", pesel: "14212808853", name: "Filip",
                    surname: "Rysztak", sex: "MALE", address: {}, stays: {id: 9},
                    birthDate: "2014-01-28", activeStay: {id: 9}
                };
                console.log("test");
                console.log($rootScope.patient);
                $http({
                    url: $rootScope.globalSettings.serverUrl + '/stay/' + $rootScope.patient.activeStay.id + '/test-panel-result',
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
                                        format: '%Y-%m-%d %H:%M'
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
                            var colmnDefs = testResults.map(function (testPanel) {
                                var columnDef = {
                                    name: testPanel.requestDate,
                                    field: '' + testPanel.requestDate + '.value',
                                    //cellTemplate: '<div class="ui-grid-cell-contents" >{{grid, row}}</div>'
                                    cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
                                        var cellVal = grid.getCellValue(row,col)
                                        console.log('row');
                                        console.log(row);
                                        console.log('colmn');
                                        console.log(col);
                                        console.log('celval');
                                        console.log(cellVal);
                                        var test = row.entity[col.name];
                                        console.log('test');
                                        console.log(test);

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
                            });
                            console.log(colmnDefs);
                            $scope.columnDefs = colmnDefs;
                        }
                    }

                    function gridOptions() {
                        $scope.gridOptions = {
                            columnDefs: $scope.columnDefs,
                            rowTemplate: 'grid-row.html',
                            data: 'gridData'
                        };
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
