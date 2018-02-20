'use strict';

// Declare app level module which depends on views, and components
angular.module('sample.app', ['ngResource', 'ui.router'])

    .config(function($httpProvider, $stateProvider) {

        // it tells the server that the client is not a browser
        // (instructs server to NOT send header to require basic authentication (Authenticate : Basic ...) and thus the browser will not pop up an authentication dialog
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        var loginState = {
            name: 'login',
            url: '/login',
            component: 'login'
        }

        var welcomeState = {
            name: 'welcome',
            url: '/welcome',
            templateUrl: '/templates/welcome.html'
        }

        $stateProvider.state(loginState);
        $stateProvider.state(welcomeState);

    })

    .component('login', {

        templateUrl : '/templates/login.html',

        controller: function() {
            this.greeting = 'hello';
            this.credentials = {};
            this.username = "";
            this.password = "";

            this.toggleGreeting = function() {
                this.greeting = (this.greeting == 'hello') ? 'whats up' : 'hello';
            }

            this.login = function() {
            console.log("USERNAME=" + this.username);
                //TODO login here
            }
        }
    })

    .factory('DocumentService', ['$resource', function($resource) {
        return $resource('document/:documentId', {documentId : '@documentId'});
    }])

    .factory('LoginService', ['$resource', function($resource) {
        return $resource('authenticate', { username: "@username", password: "@password" });
    }])

    .controller('MainController', ['$scope', 'DocumentService', 'LoginService', function ($scope, DocumentService, LoginService) {

        $scope.authenticated = false;
        $scope.document = null;

        $scope.getDocument = function() {
            console.log("Getting document...")
            DocumentService.get({documentId : '12345'}, function(result) {
                console.log("Got doc id=" + result.id + " content=" + result.content);
            });
        }

        $scope.authenticate = function() {
            console.log("Authenticate method called.");
            LoginService.save(
                { username: "wojtek", password: "abc123" },
                function(result) {
                    $scope.authenticated = true;
                    console.log("Auth result success: " + result.code);
                },
                function(result) {
                    console.log("Auth result error: " + result.code);
                }
            );
        };



        $scope.logout = function() {
        };

        $scope.open = function(documentId) {
            console.log("GET=" + documentId);
            $scope.document = DocumentService.get(
                { documentId : documentId },
                function(document) {
                    $scope.document = document;
                }
            );
        };

//        $scope.open('123');
//        $scope.authenticate();        //TODO authn on every app reload

    }]);



