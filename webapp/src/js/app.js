angular.module('ICUPad', [
  'ngRoute',
  'ngTable',
  'mobile-angular-ui',
  'checklist-model',
  'ui.bootstrap',
  'ICUPad.services.Conversion',
  'ICUPad.services.Configuration',
  'ICUPad.services.Form',
  'ICUPad.directives.Form',
  'ICUPad.directives.Field',
  'ICUPad.controllers.Create',
  'ICUPad.controllers.Header',
  'ICUPad.controllers.View',
  'ICUPad.controllers.Main',
  'ICUPad.controllers.Nurse',
  'ICUPad.controllers.NurseDiagnosis'
])

.config(function($routeProvider, $compileProvider) {
  $routeProvider.when('/', {templateUrl:'home.html',  reloadOnSearch: false});
  $routeProvider.when('/nurse', {templateUrl:'nurse.html',  reloadOnSearch: false});
  $routeProvider.when('/nurse-diagnosis', {templateUrl:'nurse-diagnosis.html',  reloadOnSearch: false});
  $routeProvider.when('/form', {templateUrl:'view.html', controller: 'ViewCtrl', reloadOnSearch: false});
  $routeProvider.when('/checkbox.html', {templateUrl:'checkbox.html', reloadOnSearch: false});

  $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|data):/);
});