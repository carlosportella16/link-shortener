package encurtador.adapter.in.web;

import encurtador.adapter.in.web.dto.CreateUserRequest;
import encurtador.adapter.in.web.dto.CreateUserResponse;
import encurtador.core.port.in.CreateUserPortIn;
import encurtador.core.port.in.DeleteUserPortIn;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserControllerAdapterIn {

    private final CreateUserPortIn createUserPortIn;
    private final DeleteUserPortIn deleteUserPortIn;

    public UserControllerAdapterIn(CreateUserPortIn createUserPortIn, DeleteUserPortIn deleteUserPortIn) {
        this.createUserPortIn = createUserPortIn;
        this.deleteUserPortIn = deleteUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest req) {

        var userCreated = createUserPortIn.execute(req.toDomain());

        var body = CreateUserResponse.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(JwtAuthenticationToken token) {
        var userId = String.valueOf(token.getTokenAttributes().get("sub"));
        deleteUserPortIn.execute(UUID.fromString(userId));

        return ResponseEntity.noContent().build();
    }
}
