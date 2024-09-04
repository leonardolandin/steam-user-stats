package br.com.steam.user.stats.infrastructure.config;

import br.com.steam.user.stats.adapters.UserAdapter;
import br.com.steam.user.stats.domain.services.AuthDomainService;
import jakarta.enterprise.context.Dependent;

@Dependent
public class InjectionConfig {

    public AuthDomainService authDomainService(UserAdapter userAdapter) {
        return new AuthDomainService(userAdapter);
    }
}
