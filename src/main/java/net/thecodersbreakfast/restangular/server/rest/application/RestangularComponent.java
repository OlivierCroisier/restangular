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

package net.thecodersbreakfast.restangular.server.rest.application;

import com.google.common.util.concurrent.AbstractIdleService;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class RestangularComponent extends AbstractIdleService {

    public static final int PORT = 8000;

    private HttpServer httpServer;

    public RestangularComponent() throws IOException {
        RestangularApplication app = new RestangularApplication();
        Set<Class<?>> resources = new LinkedHashSet<>(app.getClasses());
        resources.add(JacksonJsonProvider.class);
        ResourceConfig config = new DefaultResourceConfig(resources);
        httpServer = HttpServerFactory.create("http://localhost:" + PORT + "/", config);
    }

    @Override
    protected void startUp() throws Exception {
        httpServer.start();
        System.out.println("Server started on port " + PORT + ".");
        System.out.println("Application is now available on http://localhost:" + PORT + "/web/index.html");
    }

    @Override
    protected void shutDown() throws Exception {
        httpServer.stop(1);
    }

    public static void main(String[] args) throws Exception {
        new RestangularComponent().startAndWait();
    }
}
