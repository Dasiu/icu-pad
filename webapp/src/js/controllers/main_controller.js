angular.module('ICUPad.controllers.Main', [])

    .controller('MainController', function ($rootScope, $scope, $http, $location, configuration) {

        $rootScope.selectedDayNumber = function() {
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
            var yesterday = new Date();
            yesterday.setDate(yesterday.getDate() - 1);
            if ($rootScope.selectedDay < yesterday) {
                $rootScope.selectedDay.setDate($rootScope.selectedDay.getDate() + 1);
            }
            broadcastDayChange();
        };

        $rootScope.prevDay = function() {
            var calculateDay = $rootScope.selectedDayNumber();
            if (calculateDay && calculateDay > 1) {
                $rootScope.selectedDay.setDate($rootScope.selectedDay.getDate() - 1);
            }
            broadcastDayChange();
        };

        $rootScope.resetSelectedDay = function() {
            var selectedDay = new Date();
            selectedDay.setHours(0);
            selectedDay.setMinutes(0);
            selectedDay.setMilliseconds(0);
            $rootScope.selectedDay = selectedDay;
        };

        $rootScope.beginOf = function(date) {
            var beginOfDay = new Date(date);
            beginOfDay.setHours(0, 0, 0, 0);
            return beginOfDay;
        };

        $rootScope.endOf = function(date) {
            var endOfDay = new Date(date);
            endOfDay.setHours(23, 59, 59, 999);
            return endOfDay;
        };

        function initGlobalSettings() {
            $rootScope.globalSettings = {
                serverUrl: configuration.server
            }
        }

        function broadcastDayChange() {
            $rootScope.$broadcast('selectedDayChanged');
        }

        $scope.header = {
            collapsed: true
        };

        $scope.patientChoosed = false;

        $rootScope.resetSelectedDay();
        $scope.headerCollapsed = true;

        initGlobalSettings();
        if (!$scope.authenticated) {
            $location.path("/login");
        }

        $scope.$on('$locationChangeStart', function(event) {
            $scope.headerCollapsed = true;
        });

        $scope.toggleCollapsed = function() {
            $scope.headerCollapsed = !$scope.headerCollapsed;
        }

    });
