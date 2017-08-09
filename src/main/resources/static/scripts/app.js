'use strict';

/**
 * @ngdoc overview
 * @name PionusApp
 * @description
 * # PionusApp
 *
 * Main module of the application.
 */
angular
    .module('PionusApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.grid',
        'ui.grid.edit',
        'ui.grid.cellNav'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/home.html',
                controller: 'HomeCtrl',
                controllerAs: 'home'
            })
            .when('/users', {
                templateUrl: 'views/users.html',
                controller: 'UsersCtrl',
                controllerAs: 'users'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
