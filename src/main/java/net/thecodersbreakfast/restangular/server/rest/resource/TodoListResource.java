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

package net.thecodersbreakfast.restangular.server.rest.resource;

import net.thecodersbreakfast.restangular.server.dao.TodoRepository;
import net.thecodersbreakfast.restangular.server.model.Todo;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import java.io.IOException;
import java.util.List;

@Component // Injectable (in tests for instance)
@RestxResource // Some REST resource (will be useful, later, to generate documentation for REST services)
@PermitAll // This is just to de-activate security on this resource (ATM, no need to be logged to access this resource)
public class TodoListResource {

    private TodoRepository repository;

    // In Restx, injection is made by constructor
    public TodoListResource(TodoRepository repository) {
        this.repository = repository;
    }

    @GET("/todos")
    public List<Todo> list() {
        return repository.list();
    }

    @POST("/todos")
    public Todo create(Todo todo) throws IOException {
        repository.create(todo);
        // Would be useful to return the created Todo, with its id here...
        return todo;
    }

}
