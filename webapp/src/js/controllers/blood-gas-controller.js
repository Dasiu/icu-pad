angular.module('ICUPad.controllers.BloodGas', [])

    .controller('BloodGasController', function ($http, $rootScope, $scope, $location) {
        $scope.title = "blood gas!";

        $scope.chart = c3.generate({
            data: {
                columns: [
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            }
        });
    });