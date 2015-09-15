angular.module('ICUPad.controllers.Settings', [])

    .controller('SettingsController', function($scope, $rootScope, $http, $location, configuration){
        $scope.serverProtocol = configuration._serverProtocol ;
        $scope.serverAddress = configuration._serverAddress;
        $scope.serverPort = configuration._serverPort;

        $scope.saveConfiguration = function() {
            configuration._serverProtocol = $scope.serverProtocol;
            configuration._serverAddress = $scope.serverAddress;
            configuration._serverPort = $scope.serverPort;
            $location.path("/login");
        }
    });
