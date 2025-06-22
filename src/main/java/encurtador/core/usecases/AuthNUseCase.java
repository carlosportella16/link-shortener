package encurtador.core.usecases;

import encurtador.adapter.in.web.dto.LoginRequest;
import encurtador.adapter.in.web.dto.LoginResponse;
import encurtador.core.exception.LoginException;
import encurtador.core.port.in.AuthNPortIn;
import encurtador.core.port.out.UserRepositoryPortOut;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class AuthNUseCase implements AuthNPortIn {

    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepositoryPortOut userRepository;
    private final Long EXPIRATION_TIME = 300L;

    public AuthNUseCase(JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepositoryPortOut userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse execute(LoginRequest req) {
        // find by username
        var user = userRepository.findByEmail(req.email())
                .orElseThrow(LoginException::new);

        // check password
        var isPasswordValid = bCryptPasswordEncoder.matches(req.password(), user.getPassword());
        if (!isPasswordValid) {
            throw new LoginException();
        }

        // generate token
        var claims = JwtClaimsSet.builder()
                .subject(user.getUserId().toString())
                .issuer("link-shortener")
                .claim("email", user.getEmail())
                .expiresAt(Instant.now().plusSeconds(EXPIRATION_TIME))
                .build();

        var tokenJwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(tokenJwt, EXPIRATION_TIME);
    }
}
