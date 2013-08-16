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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/rest/todos/{todoId}")
public class TodoResource {

    private TodoRepository repository = TodoRepository.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("todoId") Long todoId) {
        Todo todo = repository.get(todoId);
        if (todo == null) {
            return Response.noContent().build();
        }
        return Response.ok(todo).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Todo todo) throws IOException {
        repository.update(todo);
    }

    @DELETE
    public void remove(@PathParam("todoId") Long todoId) {
        repository.delete(todoId);
    }


}
