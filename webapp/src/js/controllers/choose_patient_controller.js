angular.module('ICUPad.controllers.ChoosePatient', [])

    .controller('ChoosePatientController', function ($http, $rootScope, $scope, $location, configuration) {
        loadPatients();
        $scope.gridRowClick = function (row) {
            $rootScope.patient = row.entity;

            $location.path("/");
        };

        $scope.gridOptions = {
            rowTemplate: 'grid-row.html',
            //gridMenuShowHideColumns: false,
            //enableSorting: false,
            enableColumnMenus: false,
                //showColumnMenu: false,
            //showFilter: false,
            columnDefs: [
                { name:'Imię', field: 'name', width: "200", },
                { name:'Nazwisko', field: 'surname', width: "200", },
                { name:'Data urodzenia', field: 'birthDate', width: "200",},
                { name:'Data przyjęcia', field: 'activeStay.admitDate', width: "200", }
            ],
            data : 'patients'
        };
            
        function loadPatients() {
            $http({
                url: configuration.server() + '/patient',
                method: 'GET',
                params: {findOnlyActive: true}
            })
                .success(function (data) {
                    console.log(data);
                    $scope.patients = data;
                    $scope.gridOptions = data;
                    $scope.isGridDataReady = true;
                })
                .error(function (data) {
                    console.log('Failed load patients');
                    console.log(data);
                    $rootScope.error = data;
                    $location.path('/error');
                });
        }
    });
