package br.com.steam.user.stats.config;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class MongoDbConfig implements QuarkusTestResourceLifecycleManager {

    private static MongoDBContainer CONTAINER;

    @Override
    public Map<String, String> start() {
        CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:7.0"))
                .withExposedPorts(27017);

        CONTAINER.start();

        return Map.of("quarkus.mongodb.connection-string", CONTAINER.getConnectionString(),
                "quarkus.mongodb.database", "user-stats");
    }

    @Override
    public void stop() {
        if (CONTAINER != null) {
            CONTAINER.stop();
            CONTAINER = null;
        }
    }
}
