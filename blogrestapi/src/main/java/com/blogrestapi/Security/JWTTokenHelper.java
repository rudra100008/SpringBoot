package com.blogrestapi.Security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 hours in milliseconds
    private final String secret = "jwtTokenKey"; // Secret key string
    private final Key key; // Key object

    public JWTTokenHelper() {
        // Create a key from the secret string using HS512 algorithm
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Retrieve username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from JWT token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // The interface Function<Claims, T> takes object of the claims and return type T
    //<T> tells the function is generic type and T means it return type is T
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // For retrieving any information from the token, we need the secret key
    //here secrect key used to compare with the token and check the where token is correct or
    private Claims getAllClaimsFromToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build(); // Use parserBuilder
        return parser.parseClaimsJws(token).getBody(); // Use the built parser to parse claims
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // While creating the token:
    // 1. Define claims like Issuer, Expiration, Subject, and ID.
    // 2. Sign the JWT using the HS512 algorithm and the secret key.
    // 3. Compact the JWT to a URL-safe string.
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS512) // Use the new signWith method with Key object
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
