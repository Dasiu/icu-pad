angular.module('ICUPad.services.Form', [])

.service('FormService', function FormService($http) {

    var formsJsonPath = 'static-data/sample_forms.html';

    return {
        fields:[
            {
                name : 'textfield',
                value : 'Textfield'
            },
            {
                name : 'numfield',
                value : 'Numeric field'
            },
            {
                name : 'radio',
                value : 'Radio Buttons'
            },
            {
                name : 'dropdown',
                value : 'Dropdown List'
            },
            {
                name : 'multichoice',
                value : 'Multi-choice List'
            },
            {
                name : 'date',
                value : 'Date'
            },
            {
                name : 'textarea',
                value : 'Text Area'
            },
            {
                name : 'checkbox',
                value : 'Checkbox'
            },
            {
                name : 'hidden',
                value : 'Hidden'
            },
            {
                name : 'separator',
                value : 'Separator'
            }
        ],
        form:function (id) {
            // $http returns a promise, which has a then function, which also returns a promise
            return $http.get(formsJsonPath).then(function (response) {
                return response.data;
            });
        },
        forms: function() {
            return $http.get(formsJsonPath).then(function (response) {
                return response.data;
            });
        }
    };
});
