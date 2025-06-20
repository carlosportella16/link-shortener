package encurtador.adapter.in.web.dto;

import encurtador.core.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

public record CreateUserRequest(@NotBlank @Email @NotNull String email,
                                @NotBlank @NotNull @Length(min = 8, max = 64) String password,
                                @NotBlank @NotNull @Length(min = 5, max = 50) String nickname) {

    public User toDomain() {
        return new User(email, password, nickname);
    }
}
