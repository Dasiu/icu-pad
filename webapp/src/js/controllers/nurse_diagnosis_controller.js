angular.module('ICUPad.controllers.NurseDiagnosis', [])

.controller('NurseDiagnosisController', function($scope, FormService){
    $scope.title = "NurseDiagnosis";

    $scope.availableForms = [];
    $scope.currentForm = {};
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
    }
});