package encurtador.adapter.in.web.dto;

import encurtador.core.domain.User;

public record CreateUserRequest(String email,
                                String password,
                                String nickname) {

    public User toDomain() {
        return new User(email, password, nickname);
    }
}
