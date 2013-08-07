package net.thecodersbreakfast.restangular.server.rest.application;

import net.thecodersbreakfast.restangular.server.rest.resource.TodoListResource;
import net.thecodersbreakfast.restangular.server.rest.resource.TodoResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class RestangularApplication extends Application {

    public RestangularApplication() {
        setName("Restangular");
        setDescription("RESTlet + AngularJS integration");
        setOwner("thecodersbreakfast.net");
        setAuthor("Olivier Croisier");
    }

    @Override
    public Restlet createInboundRoot() {
        Directory directory = new Directory(getContext(), "clap://class/static/");
        directory.setDeeplyAccessible(true);

        Router router = new Router(getContext());
        router.attach("/web", directory);
        router.attach("/rest/todos", TodoListResource.class);
        router.attach("/rest/todos/{todoId}", TodoResource.class);
        return router;
    }

}
