package com.sudo248.apigateway.utils;

import com.sudo248.domain.common.ErrorMessage;
import com.sudo248.domain.exception.ApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String getUserIdFromToken(String token) {
        var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getId();
    }

    public boolean validateToken(String token) throws ApiException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorMessage.TOKEN_INVALID);
        } catch (ExpiredJwtException ex) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorMessage.TOKEN_EXPIRE);
        } catch (UnsupportedJwtException ex) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorMessage.TOKEN_UNSUPPORTED);
        } catch (IllegalArgumentException ex) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "JWT claims string is empty.");
        }
    }
}
