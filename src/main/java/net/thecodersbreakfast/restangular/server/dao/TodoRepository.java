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

package net.thecodersbreakfast.restangular.server.dao;

import net.thecodersbreakfast.restangular.server.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

public class TodoRepository {

    private static final Map<Long, Todo> REPOSITORY = new ConcurrentSkipListMap<>();
    private static final AtomicLong IDS = new AtomicLong(0);

    private static final TodoRepository INSTANCE = new TodoRepository();

    static {
        INSTANCE.create(new Todo(1L, "Learn AngularJS", "HTML is great for declaring static documents, but it falters when we try to use it for declaring dynamic views in web-applications. AngularJS lets you extend HTML vocabulary for your application. The resulting environment is extraordinarily expressive, readable, and quick to develop. "));
        INSTANCE.create(new Todo(2L, "Use Twitter Bootstrap", "Sleek, intuitive, and powerful mobile-first front-end framework for faster and easier web development."));
        INSTANCE.create(new Todo(3L, "Integrate with Restlet", "The leading web API framework for Java"));
    }

    public static TodoRepository getInstance() {
        return INSTANCE;
    }

    private TodoRepository() {
    }

    public List<Todo> list() {
        return new ArrayList<>(REPOSITORY.values());
    }

    public Todo get(Long id) {
        return REPOSITORY.get(id);
    }

    public void create(Todo todo) {
        long id = IDS.getAndIncrement();
        todo.setId(id);
        REPOSITORY.put(id, todo);
    }

    public void update(Todo todo) {
        REPOSITORY.put(todo.getId(), todo);
    }

    public boolean delete(Long id) {
        return REPOSITORY.remove(id) != null;
    }
}
