package br.com.steam.user.stats.application.services;

import br.com.steam.user.stats.application.dto.UserDTO;
import br.com.steam.user.stats.domain.services.AuthDomainService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AuthApplicationService {

    @Inject
    AuthDomainService authDomainService;

    public Uni<UserDTO> createUser(UserDTO userDTO) {
        return authDomainService.createUser(userDTO);
    }
}
