'use strict';

angularApp.directive('formDirective', ['conversionService', 'configuration', '$http', function (conversionService, configuration, $http) {
    return {
        controller: function($scope){
            $scope.submit = function(){
                $scope.form.submitted = true;

                conversionService.convert($scope.form).
                    then(function(converted) {
                        return $http.post(configuration + "/nurse/form", converted);
                    });
            }

            $scope.cancel = function(){
                console.log('form canceled');
            }
        },
        templateUrl: './views/directive-templates/form/form.html',
        restrict: 'E',
        scope: {
            form:'='
        }
    };
  }]);
