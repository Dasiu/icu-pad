angular.module('ICUPad.controllers.NurseDiagnosis', [])

.controller('NurseDiagnosisController', function($scope, $http, FormService, configuration){
    $scope.title = "nurse";

    $scope.availableForms = [];
    $scope.currentForm = {};

    $scope.diagnosis = [];

    FormService.getFormsForView($scope.title).then(function(forms) {
        $scope.availableForms = forms;
    });

    $scope.loadFormAndShow = function(formId) {
        var context = this;
        FormService.loadForm($scope.title, formId).
            then (function(form) {
                $scope.currentForm = form;
                context.$parent.Ui.turnOn('formModal');
            });
    };

    $scope.reload = function() {
        return $http.get(configuration.server() + "/nurse/diagnosis").then(function (response) {
            $scope.diagnosis = response.data;
        });
    };

    $scope.reload();
});
