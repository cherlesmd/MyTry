package com.charliemartinezdominguez.MyTry.auth;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charliemartinezdominguez.MyTry.config.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request,
            HttpServletResponse response) {

        var user = service.createUser(request);
        String accessToken = jwtService.generateRefreshToken(user);
        // 7 days
        ResponseCookie cookie = ResponseCookie.from("accessToken").value(accessToken).maxAge(Duration.ofSeconds(604800))
                .httpOnly(true).secure(true).path("/").sameSite("None").build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request,
            HttpServletResponse response) {

        var user = service.findUser(request);
        var accessToken = jwtService.generateToken(user);

        ResponseCookie cookie = ResponseCookie.from("accessToken").value(accessToken).maxAge(Duration.ofSeconds(604800))
                .httpOnly(true).secure(true).path("/").sameSite("None").build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(service.authenticate(user));
    }

    @PostMapping("/refresh-token")
    public void accessToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("accessToken", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");
    }
}
