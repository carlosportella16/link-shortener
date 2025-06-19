package encurtador.core.usecases;

import encurtador.core.domain.User;
import encurtador.core.exception.UserAlreadyExistException;
import encurtador.core.port.in.CreateUserPortIn;
import encurtador.core.port.out.UserRepositoryPortOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase implements CreateUserPortIn {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepositoryPortOut userRepositoryPortOut;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUserUseCase(UserRepositoryPortOut userRepositoryPortOut, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositoryPortOut = userRepositoryPortOut;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User execute(User user) {
        logger.info("Creating user: {}", user.getEmail());
        var optUser = userRepositoryPortOut.findByEmail(user.getEmail());
        if (optUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists: " + user.getEmail());
        }
        user.encodePassword(bCryptPasswordEncoder);
        var userCreated = userRepositoryPortOut.save(user);
        logger.info("Creating user: {}", user.getUserId());
        return userCreated;
    }
}
