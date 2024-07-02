package com.example.todolist.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {

    @Value(value = "${jwt.token.secret}")
    private String secretKey;

    @Value(value = "${jwt.token.lifeTime.day}")
    private Long jwtTokenLifeTime;


    public String generateToken(Authentication authentication){
        Date now = new Date();
        Long dayInMillis = 1000L * 60L * 60L * 24L;
        Date expiredAt = new Date(now.getTime() + (dayInMillis * jwtTokenLifeTime));
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        return Jwts
                .builder()
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts
                .parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .parse(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

