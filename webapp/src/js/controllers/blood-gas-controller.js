angular.module('ICUPad.controllers.BloodGas', [])

    .controller('BloodGasController', ['$http', '$rootScope', '$scope', '$location', '$timeout', 'configuration',
        function ($http, $rootScope, $scope, $location, $timeout, configuration) {
            $scope.title = "blood gas!";
            $scope.chartMode = false;
            $scope.toggleChartMode = function () {
                $scope.chartMode = !$scope.chartMode;
                if ($scope.chartMode) {
                    $timeout(generateChart, 1)
                }
                //window.dispatchEvent(new Event('resize'));
            };

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

                console.log("test");
                console.log($rootScope.patient);
                $http({
                    url: configuration.server() + '/stay/' + 9 + '/test-panel-result', // todo
                    method: 'GET'
                })
                    .success(function (data) {
                        console.log(data);
                        prepareTestResults(data);
                        $scope.testResults = data;
                    })
                    .error(function (data) {
                        console.log('Failed to load test panel result');
                        console.log(data);
                        $rootScope.error = data;
                        $location.path('/error');
                    });

                function prepareTestResults(testResults) {
                    console.log(new Date());

                    var testNamesInArrays = testResults.map(function (obj) {
                        return obj.tests.map(function (test) {
                            var testNameWithUnit = test.unit ? test.name + ' [' + test.unit + ']' : test.name;
                            test.testNameWithUnit = testNameWithUnit;
                            return testNameWithUnit
                        })
                    });
                    var testNames = [].concat.apply([], testNamesInArrays).filter(function (value, index, self) {
                        return self.indexOf(value) === index;
                    });
                    $scope.testNames = testNames;
                    //$scope.selectedTestNames = ['pO2', 'pCO2', 'pH'];
                    var selectedTestNames = JSON.parse(window.localStorage.getItem('selectedTestNames'));
                    if (!selectedTestNames) {
                        $scope.selectedTestNames = testNames;
                        window.localStorage.setItem('selectedTestNames', JSON.stringify($scope.selectedTestNames))
                    } else {
                        $scope.selectedTestNames = selectedTestNames
                    }
                    chartData();

                    gridData();
                    gridOptions();

                    function chartData() {
                        $scope.dates = testResults.map(function (testPanel) {
                            return testPanel.requestDate
                        });
                        $scope.chartData = testResults.map(function (testPanel) {
                            var obj = {requestDate: testPanel.requestDate};
                            testPanel.tests.forEach(function (test) {
                                obj[test.testNameWithUnit] = test.value
                            });
                            return obj;
                        });
                        console.log($scope.chartData);
                    }

                    function gridData() {
                        $scope.gridData = testNames.map(function (testName) {
                            var row = {
                                testName: testName
                            };
                            testResults.forEach(function (testPanel) {
                                var test = testPanel.tests.filter(function (test) {
                                    return test.testNameWithUnit === testName
                                })[0];
                                var testValue = test ? test.value : null;
                                row[testPanel.requestDate] = test;
                            });
                            return row
                        });
                        columnDefsF();
                        console.log('x');
                        console.log($scope.gridData);

                        function columnDefsF() {
                            var testNamesColumn = {
                                name: 'Badanie',
                                field: 'testName',
                                width: '180'
                            };
                            var colmnDefs = [].concat([testNamesColumn], testResults.map(function (testPanel) {
                                var requestDate = new Date(testPanel.requestDate);
                                var columnDef = {
                                    name: testPanel.requestDate,
                                    displayName: requestDate.getUTCHours() + ':' + requestDate.getUTCMinutes(),
                                    field: '' + testPanel.requestDate + '.value',
                                    width: '70',
                                    //cellTemplate: '<div class="ui-grid-cell-contents" >{{grid, row}}</div>'
                                    cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
                                        var cellVal = grid.getCellValue(row,col);
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

            function generateChart() {
                $scope.chart = c3.generate({
                    data: {
                        //x: 'x',
                        xFormat: '%Y-%m-%d %H:%M:%S',
                        json: $scope.chartData,
                        keys: {
                            x: 'requestDate',
                            value: $scope.testNames
                        }
                    },
                    axis: {
                        x: {
                            type: 'timeseries',
                            tick: {
                                format: '%H:%M'
                            }
                        }
                    },
                    legend: {
                        show: false
                    }
                });
                var oldLegend = document.getElementById('legend');
                if (oldLegend) {
                    oldLegend.parentNode.removeChild(oldLegend);
                }
                d3.select('#blood-gas-partial').insert('div', '.chart').attr('class', 'legend').attr('id', 'legend').selectAll('span')
                    .data($scope.testNames)
                    .enter().append('span')
                    .attr('data-id', function (id) {
                        console.log(id);
                        return id;
                    })
                    .html(function (id) {
                        return id;
                    })
                    .each(function (id) {
                        d3.select(this)
                            .style('background-color', $scope.chart.color(id));
                        if (!$scope.selectedTestNames.some(function(testName) {
                                return testName === id;
                            })) {
                            // disabled
                            d3.select(this).style('opacity', '0.3');
                            $scope.chart.toggle(id);
                        }
                    })
                    .on('mouseover', function (id) {
                        $scope.chart.focus(id);
                    })
                    .on('mouseout', function (id) {
                        $scope.chart.revert();
                    })
                    .on('click', function (id) {
                        if ($scope.selectedTestNames.some(function(testName) {
                                return testName === id;
                            })) {
                            $scope.selectedTestNames = $scope.selectedTestNames.filter(function(testName) {
                                return testName !== id;
                            });
                            d3.select(this).style('opacity', '0.3')
                        } else {
                            $scope.selectedTestNames.push(id);
                            d3.select(this).style('opacity', '1')
                        }
                        window.localStorage.setItem('selectedTestNames', JSON.stringify($scope.selectedTestNames));
                        //var opacity = d3.select(this).style('opacity');
                        //if (opacity === '0.3') d3.select(this).style('opacity', '1')
                        //else d3.select(this).style('opacity', '0.3')
                        $scope.chart.toggle(id);
                    });
            }
        }]);
