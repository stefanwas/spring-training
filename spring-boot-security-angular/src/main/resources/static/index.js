'use strict';

// Declare app level module which depends on views, and components
angular.module('sample.app', ['ngResource', 'ui.router'])

    .config(function($httpProvider, $stateProvider) {

        // it tells the server that the client is not a browser (instructs server to NOT send header to require basic authentication (Authenticate : Basic ...)
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        var loginState = {
            name: 'login',
            url: '/login',
            component: 'login'
        }

        var welcomeState = {
            name: 'welcome',
            url: '/welcome',
            templateUrl: 'templates/welcome.html'
        }

        $stateProvider.state(loginState);
        $stateProvider.state(welcomeState);

    })

    .component('login', {

        templateUrl : 'templates/login.html',

        controller: function() {
            this.greeting = 'hello';

            this.toggleGreeting = function() {
                this.greeting = (this.greeting == 'hello') ? 'whats up' : 'hello';
            }
        }
    })

    .factory('DocumentService', ['$resource', function($resource) {
        return $resource('document/:documentId', {documentId : '@documentId'});
    }])

    .controller('MainController', ['$scope', 'DocumentService', function ($scope, DocumentService) {

        $scope.document = null;

        $scope.open = function(documentId) {
            console.log("GET=" + documentId);
            $scope.document = DocumentService.get(
                {documentId: documentId},
                function(document) {
                    $scope.document = document;
                }
            );
        };

        $scope.open('123');
//        $scope.authenticate();        //TODO authn on every app reload

    }]);



