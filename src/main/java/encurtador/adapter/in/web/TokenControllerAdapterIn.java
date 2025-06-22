package encurtador.adapter.in.web;

import encurtador.adapter.in.web.dto.LoginRequest;
import encurtador.adapter.in.web.dto.LoginResponse;
import encurtador.core.usecases.AuthNUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenControllerAdapterIn {

    private final AuthNUseCase authNUseCase;

    public TokenControllerAdapterIn(AuthNUseCase authNUseCase) {
        this.authNUseCase = authNUseCase;
    }

    @PostMapping(value = "/oauth/token")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        var response = authNUseCase.execute(req);
        return ResponseEntity.ok(response);
    }
}
