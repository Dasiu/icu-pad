angular.module('ICUPad.controllers.BloodGas', [])

    .controller('BloodGasController', ['$http', '$rootScope', '$scope', '$location',
        function ($http, $rootScope, $scope, $location) {
            $scope.title = "blood gas!";
            $scope.chartMode = true;
            $scope.toggleChartMode = function () {
                $scope.chartMode = !$scope.chartMode
            }

            $scope.gridOptions = {
                rowTemplate: 'grid-row.html',
                data: 'gridData'
            };

            //$scope.chart = c3.generate({
            //    data: {
            //        columns: [
            //            ['data1', 30, 200, 100, 400, 150, 250],
            //            ['data2', 50, 20, 10, 40, 15, 25]
            //        ]
            //    }
            //});

            loadTestResults();
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
                    url: $rootScope.globalSettings.serverUrl + 'stay/' + $rootScope.patient.activeStay.id + '/test-panel-result',
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
                    $scope.selectedTestNames = testNames;
                    chartData();
                    generateChart();
                    gridData();

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
                                row[testPanel.requestDate] = testValue;
                            })
                            return row
                        });
                        console.log('x')
                        console.log($scope.gridData)
                    }
                }
            }


        }]);