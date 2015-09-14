angular.module('ICUPad.controllers.Login', [])

    .controller('LoginController', function ($rootScope, $scope, $http, $location) {
        $scope.showView = false;
        var authenticate = function (credentials, callback) {

            var headerVal;
            if (credentials) {
                headerVal = buildAuthorizationHeaderValue(credentials.username, credentials.password);
            } else if (window.sessionStorage.getItem('headerVal')) {
                headerVal = window.sessionStorage.getItem('headerVal');
                console.log(headerVal);
            } else {
                headerVal = undefined;
            }
            var headers = headerVal ? {
                authorization: headerVal
            } : {};

            function buildAuthorizationHeaderValue(username, password) {
                return (username || password)
                    ? "Basic " + btoa(username + ":" + password)
                    : undefined;
            }

            $http.get($rootScope.globalSettings.serverUrl + '/user/current', {headers: headers})
                .success(function (data) {
                    $rootScope.authenticated = true;
                    console.log(data);
                    $rootScope.user = data;
                    $http.defaults.headers.common['Authorization'] = headerVal;
                    window.sessionStorage.setItem('headerVal', headerVal);
                    $scope.showView = true;
                    callback && callback();
                }).error(function () {
                    $rootScope.authenticated = false;
                    $scope.showView = true;
                    callback && callback();
                });

        };

        authenticate(undefined, function () {
            if ($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/login");
            }
        });

        $scope.credentials = {};
        $scope.login = function () {
            authenticate($scope.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };
    });
