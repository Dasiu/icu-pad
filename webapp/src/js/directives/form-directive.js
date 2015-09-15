angular.module('ICUPad.directives.Form', [])

.directive('formDirective', ['conversionService', 'configuration', '$http', function (conversionService, configuration, $http) {
    return {
        controller: function($scope){
            $scope.submit = function(){

                $scope.form.submitted = true;

                conversionService.convert($scope.form).
                    then(function(converted) {
                        return $http.post(configuration.server() + "/nurse/form", converted);
                    }).
                    then(function() {
                        if ($scope.payload) {
                            $scope.payload();
                        }
                    });
            }

            $scope.cancel = function(){
                if ($scope.payload) {
                    $scope.payload();
                }
            }
        },
        templateUrl: 'directive-templates/form/form.html',
        restrict: 'E',
        scope: {
            form:'=',
            payload:'='
        }
    };
  }]);
