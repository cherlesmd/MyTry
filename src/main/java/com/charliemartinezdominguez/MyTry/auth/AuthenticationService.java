package com.charliemartinezdominguez.MyTry.auth;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.charliemartinezdominguez.MyTry.config.JwtService;
import com.charliemartinezdominguez.MyTry.user.Role;
import com.charliemartinezdominguez.MyTry.user.User;
import com.charliemartinezdominguez.MyTry.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public User createUser(RegisterRequest request) {
    var user = User.builder().username(request.getUsername())
        .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
        .build();
    return user;
  }

  public AuthenticationResponse register(User user) {
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().accessToken(jwtToken).build();
  }

  public User findUser(AuthenticationRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    return repository.findByUsername(request.getUsername()).orElseThrow();
  }

  public AuthenticationResponse authenticate(User user) {
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().accessToken(jwtToken).build();
  }

  public String extractCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("accessToken")) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String refreshToken = extractCookie(request);
    final String userName;

    userName = jwtService.extractUsername(refreshToken);
    if (userName != null) {
      var user = this.repository.findByUsername(userName).orElseThrow();

      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        var authResponse = AuthenticationResponse.builder().accessToken(accessToken).build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }

  }

}
