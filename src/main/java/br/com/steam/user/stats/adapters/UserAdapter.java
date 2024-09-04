package br.com.steam.user.stats.adapters;

import br.com.steam.user.stats.application.dto.UserDTO;
import br.com.steam.user.stats.application.mapping.UserMapper;
import br.com.steam.user.stats.application.ports.UserPort;
import br.com.steam.user.stats.infrastructure.persistence.mongo.UserEntityRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserAdapter implements UserPort {

    @Inject
    UserEntityRepository userEntityRepository;

    @Inject
    UserMapper userMapper;

    @Override
    public Uni<UserDTO> getUserByEmailAndUsername(String email, String username) {
        return Uni.createFrom().item(UserDTO.builder().build());
    }

    @Override
    public Uni<Boolean> existUserByEmail(String email) {
        return userEntityRepository.count("email = ?1", email)
                .onItem()
                .transform(aLong -> aLong != 0);
    }

    @Override
    public Uni<UserDTO> create(UserDTO userDTO) {
        return userEntityRepository.persist(userMapper.toUserEntity(userDTO))
                .onItem()
                .transform(userEntity -> userMapper.toUserDTO(userEntity));
    }
}
