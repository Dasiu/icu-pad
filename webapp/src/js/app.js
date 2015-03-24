angular.module('ICUPad', [
  'ngRoute',
  'mobile-angular-ui',
  'ui.bootstrap',
  'ICUPad.controllers.Main',
  'ICUPad.controllers.Nurse'
])

.config(function($routeProvider) {
  $routeProvider.when('/', {templateUrl:'home.html',  reloadOnSearch: false});
  $routeProvider.when('/nurse', {templateUrl:'nurse.html',  reloadOnSearch: false});
});