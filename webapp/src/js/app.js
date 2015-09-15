var app = angular.module('ICUPad', [
  'ngRoute',
  'ngTable',
  'mobile-angular-ui',
  'checklist-model',
  'ui.bootstrap',
  'ui.grid',
  'ui.grid.edit',
  'ICUPad.services.Conversion',
  'ICUPad.services.Configuration',
  'ICUPad.services.Form',
  'ICUPad.directives.Form',
  'ICUPad.directives.Field',
  'ICUPad.controllers.Create',
  'ICUPad.controllers.Header',
  'ICUPad.controllers.View',
  'ICUPad.controllers.Main',
  'ICUPad.controllers.Login',
  'ICUPad.controllers.ChoosePatient',
  'ICUPad.controllers.Nurse',
  'ICUPad.controllers.NurseDiagnosis',
  'ICUPad.controllers.Settings',
  'ICUPad.controllers.BloodGas'
])

.config(function($routeProvider, $compileProvider, $httpProvider) {
  $routeProvider.when('/', {reloadOnSearch: false});
  $routeProvider.when('/login', {templateUrl:'login.html', controller: 'LoginController', reloadOnSearch: false});
  $routeProvider.when('/nurse', {templateUrl:'nurse.html',  reloadOnSearch: false});
  $routeProvider.when('/nurse-diagnosis', {templateUrl:'nurse-diagnosis.html',  reloadOnSearch: false});
  $routeProvider.when('/settings', {templateUrl:'settings.html',  reloadOnSearch: false});
  $routeProvider.when('/choose-patient', {templateUrl:'choose-patient.html', controller: 'ChoosePatientController', reloadOnSearch: false});
  $routeProvider.when('/blood-gas', {templateUrl:'blood-gas.html', controller: 'BloodGasController', reloadOnSearch: false});
  $routeProvider.when('/form', {templateUrl:'view.html', controller: 'ViewCtrl', reloadOnSearch: false});
  $routeProvider.when('/checkbox.html', {templateUrl:'checkbox.html', reloadOnSearch: false});

  $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|data):/);

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});
