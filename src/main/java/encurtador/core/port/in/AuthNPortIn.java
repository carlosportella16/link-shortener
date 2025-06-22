package encurtador.core.port.in;

import encurtador.adapter.in.web.dto.LoginRequest;
import encurtador.adapter.in.web.dto.LoginResponse;

public interface AuthNPortIn {

    LoginResponse execute(LoginRequest req);
}
