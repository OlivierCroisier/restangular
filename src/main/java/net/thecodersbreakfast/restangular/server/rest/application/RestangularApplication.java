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
