angular.module('ICUPad.controllers.Settings', [])

    .controller('SettingsController', function($scope, $rootScope, $http, $location, configuration){
        $scope.serverProtocol = configuration._serverProtocol ;
        $scope.serverAddress = configuration._serverAddress;
        $scope.serverPort = configuration._serverPort;

        $scope.formServerProtocol = configuration._formServerProtocol ;
        $scope.formServerAddress = configuration._formServerAddress;
        $scope.formServerPort = configuration._formServerPort;

        $scope.saveConfiguration = function() {
            configuration._serverProtocol = $scope.serverProtocol;
            configuration._serverAddress = $scope.serverAddress;
            configuration._serverPort = $scope.serverPort;

            configuration._formServerProtocol = $scope.formServerProtocol;
            configuration._formServerAddress = $scope.formServerAddress;
            configuration._formServerPort = $scope.formServerPort;

            $location.path("/login");
        }
    });
