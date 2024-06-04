package quiz.cards.backend.Model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCore {
    private String secret = "mysupersecretkey2315";
    private int lifeTime = 24 * 60 * 60 * 1000; // Время жизни токена в миллисекундах

    public String generateToken(User user){
        Date date = new Date();
        return Jwts.builder().setSubject(String.valueOf(user.getId()))  // Информация
                .setIssuedAt(date)                                      // Когда выдан
                .setExpiration(new Date(date.getTime() + lifeTime))     // Срок жизни токена
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String getIdFromJwt(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
