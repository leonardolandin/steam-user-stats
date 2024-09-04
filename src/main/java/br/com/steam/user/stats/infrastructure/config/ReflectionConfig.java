package br.com.steam.user.stats.infrastructure.config;

import br.com.steam.user.stats.application.dto.UserDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets ={ UserDTO.class })
public class ReflectionConfig {
}
