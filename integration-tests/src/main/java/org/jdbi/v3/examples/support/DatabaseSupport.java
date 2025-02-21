/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.v3.examples.support;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jdbi.v3.core.Jdbi;

import io.agroal.api.AgroalDataSource;
import io.quarkus.runtime.Startup;

@Singleton
@Startup
public final class DatabaseSupport {

    @Inject
    AgroalDataSource ds;

    static public Jdbi jdbi;

    @PostConstruct
    public void setup() {
        System.out.println("done");
        jdbi = Jdbi.create(ds).installPlugins();
    }

    public static Supplier<Jdbi> supplier;

    public static void withDatabase(Consumer<Jdbi> jdbiConsumer) throws Exception {
        jdbiConsumer.accept(jdbi);
    }
}
