package br.com.steam.user.stats.application.ports;

import br.com.steam.user.stats.application.dto.UserDTO;
import io.smallrye.mutiny.Uni;

public interface UserPort {
    Uni<UserDTO> getUserByEmailAndUsername(String email, String username);
    Uni<Boolean> existUserByEmail(String email);
    Uni<UserDTO> create(UserDTO userDTO);
}
