package JeysonAmado.UsersService.Http.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtilities {

    private static final String SECRET_KEY = "Triju";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String email,Long userId){
        return JWT.create()
                .withSubject(email)
                .withIssuer("Triju")
                .withClaim("userId",userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(3)))
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt){
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);

            return true;
        }
        catch (JWTVerificationException e){
            return false;
        }
    }

    public String getEmail(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }

    public Long getUserId(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getClaim("userId").asLong();
    }

}
