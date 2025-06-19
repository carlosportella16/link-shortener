package encurtador.core.port.in;

import encurtador.adapter.in.web.dto.CreateUserRequest;
import encurtador.core.domain.User;

public interface CreateUserPortIn {
    User execute(User req);
}
