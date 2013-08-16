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

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/web")
public class StaticResource {

    private static final String ROOT_STATIC_RESOURCES = "static";

    @GET
    @Path("{path : .*}")
    public Response staticResource(@PathParam("path") String path) throws IOException {
        File file = file(path);
        if (file != null) {
            String mimeType = mimeType(file);
            return Response.ok(file, mimeType).build();
        } else {
            InputStream is = is(path);
            String mimeType = mimeType(path);
            return Response.ok(CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8)), mimeType).build();
        }
    }

    static String mimeType(String path) {
        if (path.endsWith("js")) {
            return "application/javascript";
        }
        if (path.endsWith("css")) {
            return "text/css";
        }
        return new MimetypesFileTypeMap().getContentType(path);
    }

    static String mimeType(File file) {
        String fileExtension = Files.getFileExtension(file.getName());
        if ("js".equals(fileExtension)) {
            return "application/javascript";
        }
        if ("css".equals(fileExtension)) {
            return "text/css";
        }
        return new MimetypesFileTypeMap().getContentType(file);
    }

    static InputStream is(String path) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(staticPath(path));
    }

    static String staticPath(String path) {
        return ROOT_STATIC_RESOURCES + "/" + path;
    }

    static File file(String path) throws IOException {
        String localPath = Thread.currentThread().getContextClassLoader().getResource(staticPath(path)).getFile();
        File file = new File(localPath);
        if (!file.isFile()) {
            return null;
        }
        return file;
    }
}
