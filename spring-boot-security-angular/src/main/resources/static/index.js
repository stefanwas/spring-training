'use strict';

// Declare app level module which depends on views, and components
angular.module('sample.app', ['ngResource'])

    // this is to instruct server to not send header to require basic authentication (Authenticate : Basic ...)
    // it tells the server that the client is not a browser
    .config(function($httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
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

    }]);



