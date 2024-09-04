package br.com.steam.user.stats.domain.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MongoEntity(collection = "USER")
public class UserEntity extends PanacheMongoEntityBase {
    private ObjectId id;
    private String name;
    private String username;
    private String email;
    private String steam_key;
}
