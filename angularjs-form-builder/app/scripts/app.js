'use strict';

var angularApp = angular.module('angularjsFormBuilderApp', ['ngRoute', 'ui.bootstrap', '$strap.directives', 'checklist-model']);

angularApp.config(function ($routeProvider, $compileProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'views/create.html',
            controller: 'CreateCtrl'
        })
        .when('/forms/:id/view', {
            templateUrl: 'views/view.html',
            controller: 'ViewCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|data):/);

}).run(['$rootScope',  function() {}]);


