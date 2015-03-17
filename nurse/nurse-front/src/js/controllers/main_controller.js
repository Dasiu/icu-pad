angular.module('ICUPad.controllers.Main', [])

.controller('MainController', function($scope){
  $scope.header = {
      collapsed : true
  };
  $scope.user = {
      name : "Jan Kowalski"
  };
  $scope.patient = {
      name: "Adam Nowicki",
	  blood: "Rhd+",
	  stayStartDate: "2014-11-03",
	  birthDate: "2012-10-12",
	  weight: "15,6kg",
	  others: "uczulenie na penicylinę",
      day: 2
  }
});