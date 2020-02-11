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

public final class AuthenticationService {
  static final long EXPIRATION = 86_400_000; // 1 day
  static final String SINGNIN_GKEY = Base64.getEncoder().encodeToString("SecretValue".getBytes());
  static final String PREFIX = "Bearer";

  private AuthenticationService() {
    // not called, is an utility class and is final 2
  }

  public static void addToken(HttpServletResponse servletResponse, String username) {
    String jwtToken =
        Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(SignatureAlgorithm.HS512, SINGNIN_GKEY)
            .compact();
    servletResponse.addHeader("Authorization", PREFIX + " " + jwtToken);
  }

  public static Authentication getAuthentication(HttpServletRequest servletRequest) {
    String token = servletRequest.getHeader("Authorization");
    if (token != null) {
      String user =
          Jwts.parser()
              .setSigningKey(SINGNIN_GKEY)
              .parseClaimsJws(token.replace(PREFIX, ""))
              .getBody()
              .getSubject();
      if (user != null)
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
    return null;
  }
}
