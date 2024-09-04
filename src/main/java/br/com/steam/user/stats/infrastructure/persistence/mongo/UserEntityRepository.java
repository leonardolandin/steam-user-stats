package br.com.steam.user.stats.infrastructure.persistence.mongo;

import br.com.steam.user.stats.domain.entities.UserEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserEntityRepository implements ReactivePanacheMongoRepository<UserEntity> {
}
