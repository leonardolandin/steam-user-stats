package br.com.steam.user.stats.domain.services;

import br.com.steam.user.stats.application.dto.UserDTO;
import br.com.steam.user.stats.application.ports.UserPort;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class AuthDomainService {

    @Inject
    UserPort userPort;

    public Uni<UserDTO> createUser(UserDTO userDTO) {
        return validateUser(userDTO)
                .flatMap(_ -> userPort.existUserByEmail(userDTO.getEmail())
                        .flatMap(exists -> {
                            if (!exists) {
                                return userPort.create(userDTO);
                            }

                            return Uni.createFrom().failure(new BadRequestException("Ocorreu um erro inesperado"));
                        })
                );
    }

    private Uni<Void> validateUser(UserDTO userDTO) {
        if (StringUtil.isNullOrEmpty(userDTO.getName())) {
            return Uni.createFrom().failure(new BadRequestException("É necessário informar um nome"));
        }

        if (StringUtil.isNullOrEmpty(userDTO.getEmail()) || !userDTO.getEmail().contains("@")) {
            return Uni.createFrom().failure(new BadRequestException("É necessário informar um e-mail"));
        }

        if (StringUtil.isNullOrEmpty(userDTO.getUsername())) {
            return Uni.createFrom().failure(new BadRequestException("É necessário informar um nome de usuário"));
        }

        return Uni.createFrom().voidItem();
    }
}
