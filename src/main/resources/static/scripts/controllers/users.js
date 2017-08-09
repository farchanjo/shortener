'use strict';

/**
 * @ngdoc function
 * @name PionusApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the PionusApp
 */
angular.module('PionusApp')
    .controller('UsersCtrl', function ($scope, $log) {
        $scope.gridOptions = {};
        $scope.gridOptions.enableCellEditOnFocus = true;

        $scope.gridOptions.columnDefs = [
            {name: 'name', enableCellEditOnFocus: true, displayName: 'name', width: 200},
            {name: 'email', enableCellEditOnFocus: true, displayName: 'email', width: 200},
            {name: 'password', enableCellEditOnFocus: true, displayName: 'password', width: 200},
            {name: 'enable', enableCellEditOnFocus: true, displayName: 'enable', width: 200},
            {name: 'role', enableCellEditOnFocus: true, displayName: 'role', width: 200},
            {name: 'created', enableCellEditOnFocus: false, displayName: 'created', width: 200},
            {name: 'modified', enableCellEditOnFocus: false, displayName: 'modified', width: 200}
        ];

        $scope.gridOptions.data = [
            {
                'id': 1,
                'name': 'Fabricio Archanjo Fonseca',
                'email': 'farchanjo@gmail.com',
                'password': '',
                'enable': true,
                'role': 'ADMIN',
                'created': new Date(),
                'modified': new Date()
            }
        ];
    });
