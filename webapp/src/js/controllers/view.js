angular.module('ICUPad.controllers.View', [])

.controller('ViewCtrl', function ($scope, FormService, $routeParams) {
    $scope.form = {};
	// read form with given id
	FormService.form($routeParams.id).then(function(form) {
		$scope.form = form;
	});
	$scope.downloadJSON = function(string) {
		return "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(string));
	}
});