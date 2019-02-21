'use strict';

// Declare app level module which depends on views, and components
angular.module('sample.app', ['ngResource', 'ui.router'])

    .config(function($httpProvider, $stateProvider, $urlRouterProvider) {

        // it tells the server that the client is not a browser
        // (instructs server to NOT send header to require basic authentication (Authenticate : Basic ...) and thus the browser will not pop up an authentication dialog
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';  // is this needed ?

        var loggedInState = {
            name: 'in',
            url: '/in',

            views: {
                'header-view': {
                    component: 'headerComponent'
                },
                'login-view': {
                    component: 'welcomeComponent'
                }
            },
            params: {
            // TODO why is it null ?
                user : null,
                authenticated : true
            }
        };

        var loggedOutState = {
            name: 'out',
            url: '/out',
            views: {
                'header-view': {
                    component: 'headerComponent'
                },
                'welcome-view': {
                    component: 'loginComponent'
                }
            },
            params: {
                user:null,
                authenticated : false
            }
        };


        $stateProvider.state(loggedInState);
        $stateProvider.state(loggedOutState);
//        $urlRouterProvider.otherwise('out');

    })

    .factory('DocumentService', ['$resource', function($resource) {
        return $resource('document/:documentId', {documentId : '@documentId'});
    }])

    .factory('UserService', ['$resource', function($resource) {
        return $resource('user');
    }])

    .factory('LoginService', ['$resource', function($resource) {
        return $resource('login', { username: "@username", password: "@password" });
    }])

    .factory('LogoutService', ['$resource', function($resource) {
        return $resource('logout');
    }])


    // this component will be displayed always - when user logged in or out
    .component('headerComponent', {

        templateUrl : '/templates/header.html',

        controller: ['$scope', '$state', '$stateParams', 'LogoutService', function($scope, $state, $stateParams, LogoutService) {
            $scope.authenticated = $stateParams.authenticated;
            $scope.user = $stateParams.user;

            this.logout = function() {

                console.log("header logout called.");

                LogoutService.get({}, function(result) {
                    $scope.authenticated = false;
                    console.log("Logout result: " + result)
                    $state.go('out', {user: null, authenticated: false});
                });
            };
        }]
    })

    // this component will be displayed when user logged out
    .component('loginComponent', {

        templateUrl : '/templates/login.html',

        controller: ['$scope', '$state', 'LoginService', function($scope, $state, LoginService) {
            $scope.user = null;     // it is also possible to use this instead of $scope

            $scope.credentials = {};
            $scope.credentials.username = "";
            $scope.credentials.password = "";

            $scope.login = function() {
                console.log("login called. Username=" + $scope.credentials.username + ", Password=" + $scope.credentials.password);

                LoginService.save(
                    { username: $scope.credentials.username, password: $scope.credentials.password },
                    function(result) {
                        $scope.authenticated = true;
                        console.log("Login result success: " + result.name);
                        $state.go('in', {user: $scope.credentials.username, authenticated: true});
                    },
                    function(result) {
                    //TODO add login error
                        $scope.authenticated = false;
                        console.log("Login result error: " + result.code);
                    }
                );


            };
        }]
    })

    // this component will be displayed when user logged in
    .component('welcomeComponent', {
        templateUrl: '/templates/welcome.html',

        controller: function($scope, $state, $stateParams) {
            // this is used to be able to access {{user}} from the template
            $scope.user = $stateParams.user;
        }
    })


    .controller('MainController',
            ['$scope', '$state', 'DocumentService', 'LoginService', 'LogoutService', 'UserService',
            function ($scope, $state, DocumentService, LoginService, LogoutService, UserService) {

        $scope.authenticated = false;
        $scope.document = null;

        $scope.getDocument = function() {
            console.log("Getting document...")
            DocumentService.get({documentId : '12345'}, function(result) {
                console.log("Got doc id=" + result.id + " content=" + result.content);
            });
        }

        $scope.login = function() {
            console.log("Main Login method called.");
            LoginService.save(
                { username: "wojtek", password: "abc123" },
                function(result) {
                    $scope.authenticated = true;
                    console.log("Login result success: " + result.name);
                },
                function(result) {
                    $scope.authenticated = false;
                    console.log("Login result error: " + result.code);
                }
            );
        };


        $scope.logout = function() {
            console.log("Main Logout method called.");
            LogoutService.get({}, function(result) {
                $scope.authenticated = false;
                console.log("Logout result: " + result)
            });
        };

        $scope.open = function(documentId) {
            console.log("OPEN DOC ID=" + documentId);
            $scope.document = DocumentService.get(
                { documentId : documentId },
                function(document) {
                    $scope.document = document;
                }
            );
        };

        UserService.get({},
            function(result) {
                $scope.authenticated = true;
                console.log("Get user success: " + result.principal.username);
                $state.go('in', {authenticated: true, user: result.principal.username})
            },
            function(result) {
                $scope.authenticated = false;
//                console.log("Get user failed: " + result.code) // to nie działa
                $state.go('out', {authenticated: false, user: null})
            }
        );

//        $scope.open('123');
//        $scope.authenticate();        //TODO authn on every app reload

    }]);



