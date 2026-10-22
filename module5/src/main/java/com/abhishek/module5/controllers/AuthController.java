package com.abhishek.module5.controllers;

import com.abhishek.module5.dto.LoginDTO;
import com.abhishek.module5.dto.SignUpDTO;
import com.abhishek.module5.dto.UserDTO;
import com.abhishek.module5.services.AuthService;
import com.abhishek.module5.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = userService.signUp(signUpDTO);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                        HttpServletResponse response) {
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
