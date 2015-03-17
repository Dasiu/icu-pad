angular.module('ICUPad.controllers.Nurse', [])

.controller('NurseController', function($scope){
  $scope.title = "asdf!";
  
  $scope.hours = [7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,1,2,3,4,5,6];
  
  $scope.data = [ 
        {
        	id: 1,
        	name: "Stan psychospołeczny, rozwój psychoruchowy",
        	activities: [
             	{
             		id: 1,
             		name: "Komunikacja werbalna / niewerbalna"
             	},
             	{
             		id: 2,
             		name: "Zastępcze metody komunikacji"
             	},
             	{
             		id: 3,
             		name: "Zapewnienie bezpieczeństwa"
             	}
            ]
        },
        {
        	id: 2,
        	name: "Oddychanie",
        	activities: [
	            {
		            id: 4,
		            name: "Komunikacja werbalna / niewerbalna"
	            },
	            {
		            id: 5,
		            name: "Zastępcze metody komunikacji"
	            },
	            {
		            id: 6,
		            name: "Zapewnienie bezpieczeństwa"
	            }
            ]
        },
        {
        	id: 3,
        	name: "Żywienie",
        	activities: [
	            {
	            	id: 7,
	            	name: "Komunikacja werbalna / niewerbalna"
	            },
	            {
	            	id: 8,
	            	name: "Zastępcze metody komunikacji"
	            },
	            {
	            	id: 9,
	            	name: "Zapewnienie bezpieczeństwa"
	            }
            ]
        }
   ];
});