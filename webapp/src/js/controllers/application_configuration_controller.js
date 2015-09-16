angular.module('ICUPad.controllers.ApplicationConfigurationController', [])

    .controller('ApplicationConfigurationController', function($scope, $http, configuration){
        $scope.message = "Zapisz";
        $scope.serverOrigin = configuration.server;

        $scope.save = function() {
            window.localStorage.setItem('serverOrigin', $scope.serverOrigin);
            configuration.loadConfiguration()
            $scope.message = "Zapisano"
        }
    });