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
