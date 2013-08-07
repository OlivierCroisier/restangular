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
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class TodoListResource extends ServerResource {

    private TodoRepository repository = TodoRepository.getInstance();

    @Get
    public Representation list() {
        return new JacksonRepresentation<>(repository.list());
    }

    @Post("json")
    public void create(Representation representation) throws IOException {
        JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
        Todo todo = jsonRepresentation.getObject();
        repository.create(todo);
    }

}
