package in.aorder.qr.util.common;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    @Value("${jwt.secret}")
    private static String SECRET_KEY;

    private static final Logger LOG = LogManager.getLogger(JwtUtil.class);

    /**
     * Utility method to create a Jwt with provided claims.
     *
     * @param claims Claims to stored in Jwt
     * @return Jwt string
     */
    public static String createJwt(Claims claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new Date())
                .signWith(signingKey)
                .addClaims(claims);

        return builder.compact();
    }

    /**
     * Utility method to decode a Jwt string.
     *
     * @param jwt string
     * @return Claims from jwt
     */
    public static Claims decodeJwt(String jwt) {
        Claims claims = null;

        try {
             claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(jwt).getBody();
        }
        catch (JwtException ex) {
            LOG.error("Failed to decode Jwt: " + jwt, ex);
        }

        return claims;
    }
}
