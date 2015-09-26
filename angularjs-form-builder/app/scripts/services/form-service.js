'use strict';

angularApp.service('FormService', function FormService($http) {

    var formsJsonPath = './static-data/sample_forms.json';

    return {
        fields:[
            {
                name : 'textfield',
                value : 'Pole tekstowe'
            },
            {
                name : 'numfield',
                value : 'Pole numeryczne'
            },
            {
                name : 'radio',
                value : 'Przycisk radio'
            },
            {
                name : 'dropdown',
                value : 'Lista rozwiajana'
            },
            {
                name : 'multichoice',
                value : 'Pole wielokrotnego wyboru'
            },
            {
                name : 'date',
                value : 'Pole daty'
            },
            {
                name : 'textarea',
                value : 'Obszar tekstowy'
            },
            {
                name : 'checkbox',
                value : 'Pole wyboru'
            },
            {
                name : 'hidden',
                value : 'Pole ukryte'
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
