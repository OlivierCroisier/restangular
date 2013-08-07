package net.thecodersbreakfast.restangular.server.rest.resource;

import net.thecodersbreakfast.restangular.server.dao.TodoRepository;
import net.thecodersbreakfast.restangular.server.model.Todo;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class TodoResource extends ServerResource {

    private TodoRepository repository = TodoRepository.getInstance();

    private Long todoId;

    @Override
    protected void doInit() throws ResourceException {
        this.todoId = Long.valueOf(getAttribute("todoId"));
    }

    @Get
    public Representation get() {
        Todo todo = repository.get(todoId);
        if (todo == null) {
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
        }
        return new JacksonRepresentation<>(todo);
    }

    @Put("json")
    public void update(Representation representation) throws IOException {
        JacksonRepresentation<Todo> jsonRepresentation = new JacksonRepresentation<Todo>(representation, Todo.class);
        Todo todo = jsonRepresentation.getObject();
        repository.update(todo);
    }

    @Delete
    public void remove() {
        repository.delete(todoId);
    }


}
