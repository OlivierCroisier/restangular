package net.thecodersbreakfast.restangular.server.rest.application;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class RestangularComponent extends Component {

    public static void main(String[] args) throws Exception {
        new RestangularComponent().start();
    }

    public RestangularComponent() {
        Server server = new Server(Protocol.HTTP, 8000);
        getServers().add(server);
        //server.getContext().getParameters().set("tracing", "true");

        getClients().add(Protocol.CLAP);

        getDefaultHost().attachDefault(new RestangularApplication());
    }
}
