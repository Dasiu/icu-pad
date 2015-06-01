'use strict';

// coffeescript's for in loop
var __indexOf = [].indexOf || function(item) {
        for (var i = 0, l = this.length; i < l; i++) {
            if (i in this && this[i] === item) return i;
        }
        return -1;
    };

angularApp.directive('fieldDirective', function($http, $compile) {

    var getTemplateUrl = function(field) {
        var type = field.field_type;
        var templateUrl = './views/directive-templates/field/';
        var supported_fields = [
            'textfield',
            'numfield',
            'email',
            'textarea',
            'checkbox',
            'date',
            'dropdown',
            'hidden',
            'radio',
            'multichoice'
        ]

        if (__indexOf.call(supported_fields, type) >= 0) {
            return templateUrl += type + '.html';
        }
    }

    var linker = function(scope, element) {
        // GET template content from path
        var templateUrl = getTemplateUrl(scope.field);
        $http.get(templateUrl).success(function(data) {
            element.html(data);
            $compile(element.contents())(scope);
        });
    }

    return {
        controller: function($scope){
            $scope.minValue = function(field) {
                return field.field_lower_bound != null ? field.field_lower_bound : -2147483648;
            }
            $scope.maxValue = function(field) {
                return field.field_upper_bound != null ? field.field_lower_bound : 2147483648;
            }
            $scope.textPattern = function(field) {
                return ".{".concat(field.field_lower_bound != null ? field.field_lower_bound : 0, ",", field.field_upper_bound != null ? field.field_upper_bound : "", "}");
            }
        },
        template: '<div>{{field}}</div>',
        restrict: 'E',
        scope: {
            field: '='
        },
        link: linker
    };
});