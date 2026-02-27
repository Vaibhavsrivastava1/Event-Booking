package com.vaibhav.event_booking.security;





import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.vaibhav.event_booking.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

     private final String SECRET = "mysecretkeymysecretkeymysecretkey";

     private final Key key =  Keys.hmacShaKeyFor(SECRET.getBytes());

     public String generateToken(User user ){

                System.out.println(user.getRole().name()); 

        return Jwts.builder()
              .setSubject(user.getEmail())
              .claim( "role" , user.getRole().name())
              .setIssuedAt(new Date())
              .setExpiration(new Date( System.currentTimeMillis() + 1000*60*60))
              .signWith(key, SignatureAlgorithm.HS256)
              .compact();
     }

       public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
}



}
