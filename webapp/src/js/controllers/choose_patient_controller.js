angular.module('ICUPad.controllers.ChoosePatient', [])

    .controller('ChoosePatientController', function ($http, $rootScope, $scope, $location) {
        $scope.title = "asdf!";

        loadPatients();
        $scope.gridRowClick = function (row) {
            $rootScope.patient = row.entity;

            $location.path("blood-gas");
            //console.log($rootScope.patient);
            //console.log($rootScope.authenticated);
            //console.log($rootScope.authenticated && $rootScope.patient);
        };

        $scope.gridData = [];

        $scope.gridOptions = {
            rowTemplate: 'grid-row.html',
            columnDefs: [
                { name:'Imię', field: 'name' },
                { name:'Nazwisko', field: 'surname' },
                { name:'Data urodzenia', field: 'birthDate'},
                { name:'Data przyjęcia', field: 'activeStay.admitDate'}
            ],
            data : $scope.gridData
        };
            
        function loadPatients() {
            $http({
                url: $rootScope.globalSettings.serverUrl + 'patient',
                method: 'GET',
                params: {findOnlyActive: true}
            })
                .success(function (data) {
                    console.log(data);
                    $scope.patients = data;
                    $scope.patients.forEach(function (patient) {
                        console.log(patient);
                        $scope.gridData.push(patient);
                    });

                    $scope.isGridDataReady = true;
                })
                .error(function () {
                    console.log('Failed load patients');
                });
        }
    });