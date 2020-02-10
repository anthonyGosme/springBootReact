package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService {
  static final long EXPIRATIONTIME = 86_400_000; // 1 day
  static final String SINGNINGKEY = Base64.getEncoder().encodeToString("SecretValue".getBytes());

  static final String PREFIX = "Bearer";

  public static void addToken(HttpServletResponse servletResponse, String username) {
    String jwtToken =
        Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, SINGNINGKEY)
            .compact();
    servletResponse.addHeader("Authorization", PREFIX + " " + jwtToken);
  }

  public static Authentication getAuthentication(HttpServletRequest servletRequest) {
    String token = servletRequest.getHeader("Authorization");
    if (token != null) {
      String user =
          Jwts.parser()
              .setSigningKey(SINGNINGKEY)
              .parseClaimsJws(token.replace(PREFIX, ""))
              .getBody()
              .getSubject();
      if (user != null)
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
    return null;
  }
}
