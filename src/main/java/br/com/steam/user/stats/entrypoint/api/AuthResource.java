package br.com.steam.user.stats.entrypoint.api;

import br.com.steam.user.stats.application.dto.UserDTO;
import br.com.steam.user.stats.application.services.AuthApplicationService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@ApplicationScoped
public class AuthResource {

    @Inject
    AuthApplicationService authApplicationService;

    @POST
    @Path("/token")
    @Produces(MediaType.TEXT_PLAIN)
    public String token() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Path("/create-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
        public Uni<UserDTO> create(UserDTO userDTO) { return authApplicationService.createUser(userDTO); }
}
