package encurtador.core.usecases;

import encurtador.core.port.in.DeleteUserPortIn;
import encurtador.core.port.out.UserRepositoryPortOut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserUseCase implements DeleteUserPortIn {

    private final UserRepositoryPortOut userRepositoryPortOut;

    public DeleteUserUseCase(UserRepositoryPortOut userRepositoryPortOut) {
        this.userRepositoryPortOut = userRepositoryPortOut;
    }

    @Override
    public void execute(UUID userId) {
        userRepositoryPortOut.deleteById(userId);
    }
}
