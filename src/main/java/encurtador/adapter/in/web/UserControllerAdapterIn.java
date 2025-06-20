package encurtador.adapter.in.web;

import encurtador.adapter.in.web.dto.CreateUserRequest;
import encurtador.adapter.in.web.dto.CreateUserResponse;
import encurtador.core.port.in.CreateUserPortIn;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserControllerAdapterIn {

    private final CreateUserPortIn createUserPortIn;

    public UserControllerAdapterIn(CreateUserPortIn createUserPortIn) {
        this.createUserPortIn = createUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest req) {

        var userCreated = createUserPortIn.execute(req.toDomain());

        var body = CreateUserResponse.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }
}
