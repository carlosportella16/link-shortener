package encurtador.core.port.out;

import encurtador.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPortOut {

    User save(User user);
    Optional<User> findByEmail(String email);

    void deleteById(UUID userId);

    Optional<User> findbyId(UUID userId);
}
