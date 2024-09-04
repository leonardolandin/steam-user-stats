package br.com.steam.user.stats.application.mapping;

import br.com.steam.user.stats.application.dto.UserDTO;
import br.com.steam.user.stats.domain.entities.UserEntity;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    UserDTO toUserDTO(UserEntity userEntity);


    @Mapping(target = "id", source = "id", qualifiedByName = "stringToObjectId")
    UserEntity toUserEntity(UserDTO userDTO);

    @Named("objectIdToString")
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}
