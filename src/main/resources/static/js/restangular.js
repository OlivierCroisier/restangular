/*
 * Copyright 2013 Olivier Croisier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var app = angular.module('restangular.app', ['ngResource']);

// ============================== APPLICATION ==============================

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider.when('/list', {templateUrl: 'view/list.html', controller: 'ListController'});
    $routeProvider.when('/add', {templateUrl: 'view/add.html', controller: 'AddController'});
    $routeProvider.when('/:id/edit', {templateUrl: 'view/add.html', controller: 'EditController'});
    $routeProvider.when('/:id', {templateUrl: 'view/display.html', controller: 'DisplayController'});
    $routeProvider.otherwise({redirectTo: '/list'});
    $locationProvider.hashPrefix('!'); // Enable ajax crawling
});

// ============================== RESOURCES ==============================

app.factory('Todo', ['$resource', function ($resource) {
    return $resource(
        '../rest/todos/:id', { 'id': '@id'}, {'update': {method: 'PUT'} });
}]);

// ============================== CONTROLLERS ==============================

app.controller('ListController', ['$scope', 'Todo', '$location', function ($scope, Todo, $location) {
    $scope.todos = Todo.query();
    $scope.deleteTodo = function (todo) {
        todo.$delete(function () {
            $location.path("/list");
        });
    };
    $scope.toggleTodo = function (todo) {
        todo.$update(function () {
            $location.path('/list');
        });
    };
    $scope.todosLeft = function () {
        return $scope.todos.filter(function (t) {
            return ! t.done;
        });
    };
}]);

app.controller('AddController', ['$scope', 'Todo', '$routeParams', '$location', function ($scope, Todo, $routeParams, $location) {
    $scope.todo = new Todo();
    $scope.saveTodo = function () {
        Todo.save($scope.todo, function () {
            $location.path('/list');
        });
    };
}]);

app.controller('EditController', ['$scope', 'Todo', '$routeParams', '$location', function ($scope, Todo, $routeParams, $location) {
    $scope.todo = Todo.get({id: $routeParams.id});
    $scope.saveTodo = function () {
        Todo.update($scope.todo, function () {
            $location.path('/list');
        });
    };
}]);

app.controller('DisplayController', ['$scope', 'Todo', '$routeParams', function ($scope, Todo, $routeParams) {
    $scope.todo = Todo.get({id: $routeParams.id});
}]);

