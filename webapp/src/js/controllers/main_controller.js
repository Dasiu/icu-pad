angular.module('ICUPad.controllers.Main', [])

    .controller('MainController', function ($rootScope, $scope, $http, $location) {
        $scope.header = {
            collapsed: true
        };
        //$scope.user = {
        //    name: "Jan Kowalski"
        //};
        //$scope.authenticated = true;

        $scope.patientChoosed = false;

        $rootScope.test = 'testJestTest';
        //$scope.patient = {
        //    name: "Adam Nowicki",
        //    blood: "Rhd+",
        //    stayStartDate: "2014-11-03",
        //    birthDate: "2012-10-12",
        //    weight: "15,6kg",
        //    others: "uczulenie na penicylinę",
        //    day: 2
        //}

        initGlobalSettings();
        if (!$scope.authenticated) {
            $location.path("/login");
        }

        function initGlobalSettings() {
            $rootScope.globalSettings = {
                serverUrl: 'https://localhost:8443/'
            }
        }

    });