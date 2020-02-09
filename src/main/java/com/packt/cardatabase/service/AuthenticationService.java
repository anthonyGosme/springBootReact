package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;

public class AuthenticationService {
  static final long EXPIRATIONTIME = 86_400_000; // 1 day
  static final String SINGNINGKEY = Base64.getEncoder().encodeToString("SecretValue".getBytes());
  static final String PREFIX = "Bearer";

  public static void addToken(HttpServletResponse res, String username) {
    String jwtToken =
        Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.ES512, SINGNINGKEY)
            .compact();
    res.addHeader("Authorisation", PREFIX + " " + jwtToken);
  }
}
