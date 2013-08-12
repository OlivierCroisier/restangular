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

import com.google.common.base.Charsets;
import restx.SignatureKey;
import restx.factory.Module;
import restx.factory.Provides;

@Module
public class RestangularApplication {

    public RestangularApplication() {
    }

    @Provides
    public SignatureKey signatureKey() {
         return new SignatureKey("-7000294811607077523 restangular restangular d7dab1c6-4a86-4c01-9fa6-2cj20d9bb9d0".getBytes(Charsets.UTF_8));
    }

}
