package io.rl.tm_api.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    private String SECRET_KEY = "raghav_rockzzz";    

    public String extractUsername(String token) {
        // System.out.println(">>>before");
        String s = extractClaim(token, Claims::getSubject);
        // System.out.println(">>>"+s);
        return s;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);      
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        System.out.println(">>>inside extract claims!");
        final Claims claims = extractAllClaims(token);
        // System.out.println(">>>extracted claims!");
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());   
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);    
    }

    private Claims extractAllClaims(String token) {
        Claims claims = null;
//        System.out.println(">>>inside extract all claims!");
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch(Exception e) {
            System.out.println(">>>token"+token+" "+e.getMessage());
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+100000000))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

}
