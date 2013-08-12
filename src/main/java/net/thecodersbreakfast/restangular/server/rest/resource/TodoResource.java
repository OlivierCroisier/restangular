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

import com.google.common.base.Optional;
import net.thecodersbreakfast.restangular.server.dao.TodoRepository;
import net.thecodersbreakfast.restangular.server.model.Todo;
import restx.Status;
import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import java.io.IOException;

@Component // Injectable (in tests for instance)
@RestxResource // Some REST resource (will be useful, later, to generate documentation for REST services)
@PermitAll // This is just to de-activate security on this resource (ATM, no need to be logged to access this resource)
public class TodoResource {

    private TodoRepository repository;

    // In Restx, injection is made by constructor
    public TodoResource(TodoRepository repository) {
        this.repository = repository;
    }

    @GET("/todos/{todoId}")
    public Optional<Todo> get(Long todoId) {
        // Transforming Todo into an optional
        // If absent, will return a 404, otherwise the Todo will be returned
        // Note that it would be cooler to have an Optional returned by the TodoRepository
        // but I didn't want to change TodoRepository contract for the moment
        return Optional.of(repository.get(todoId));
    }

    @PUT("/todos/{todoId}")
    // Restx doesn't allow void return type (it won't compile if this is the case)
    // In our case, we'll return the updated Todo
    public Todo update(Long todoId, Todo todo) throws IOException {
        repository.update(todo);
        return todo;
    }

    @DELETE("/todos/{todoId}")
    // Restx doesn't allow void return type (it won't compile if this is the case)
    // In our case, we'll return a "deleted" status
    public Status remove(Long todoId) {
        repository.delete(todoId);
        return Status.of("deleted");
    }

}
