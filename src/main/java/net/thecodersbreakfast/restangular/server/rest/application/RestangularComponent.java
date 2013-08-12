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

import com.google.common.base.Optional;
import restx.server.JettyWebServer;
import restx.server.WebServer;

public class RestangularComponent {

    // Wondering if this is really needed in our case ... would need @xavierhanin to have a look at it,
    // I'm sure there is something in restx allowing to not have to define some web.xml to make it work ! :-)
    public static final String WEB_INF_LOCATION = "src/main/webapp/WEB-INF/web.xml";
    public static final String WEB_APP_LOCATION = "src/main/webapp/static";

    public static void main(String[] args) throws Exception {
        int port = Integer.valueOf(Optional.fromNullable(System.getenv("PORT")).or("8000"));
        WebServer server = new JettyWebServer(WEB_INF_LOCATION, WEB_APP_LOCATION, port, "0.0.0.0");
        server.startAndAwait();

        System.out.println("Server started on port 8000.");
        System.out.println("Application is now available on http://localhost:8000/index.html");
    }
}
