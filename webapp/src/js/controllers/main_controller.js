angular.module('ICUPad.controllers.Main', [])

    .controller('MainController', function ($rootScope, $scope, $http, $location, configuration) {

        this.currentDay = function() {
            var temp = new Date();
            temp.setHours(0);
            temp.setMinutes(0);
            temp.setMilliseconds(0);
            return temp;
        };

        $scope.header = {
            collapsed: true
        };
        //$scope.user = {
        //    name: "Jan Kowalski"
        //};
        //$scope.authenticated = true;

        $scope.patientChoosed = false;

        $rootScope.test = 'testJestTest';
        $rootScope.selectedDay = this.currentDay();

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
                serverUrl: configuration.server
            }
        }

        $rootScope.calculateDay = function() {
            if (!$rootScope.patient || !$rootScope.patient.activeStay.admitDate || !$rootScope.selectedDay) {
                return null;
            }

            var admitDate = new Date($rootScope.patient.activeStay.admitDate.substring(0,10));
            var currentDay = $rootScope.selectedDay;

            var timeDiff = Math.abs(currentDay.getTime() - admitDate.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

            if (diffDays < 2 && currentDay.getDate() == admitDate.getDate()) {
                diffDays = 0;
            }

            return diffDays + 1;
        };

        $rootScope.nextDay = function() {
            console.log("next");
            var yesterday = new Date();
            yesterday.setDate(yesterday.getDate() - 1);
            if ($rootScope.selectedDay < yesterday) {
                console.log("true");
                $rootScope.selectedDay.setDate($rootScope.selectedDay.getDate() + 1);
            }
        };

        $rootScope.prevDay = function() {
            console.log("prev");
            var calculateDay = $rootScope.calculateDay();
            if (calculateDay && calculateDay > 1) {
                console.log("true");
                $rootScope.selectedDay.setDate($rootScope.selectedDay.getDate() - 1);
            }
        };

    });
