package encurtador.core.port.in;

import java.util.UUID;

public interface DeleteUserPortIn {

    void execute(UUID userId);
}
