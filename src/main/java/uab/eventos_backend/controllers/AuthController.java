package uab.eventos_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.response.AuthResponse;
import uab.eventos_backend.request.LoginUserEntity;
import uab.eventos_backend.request.RegisterUserEntity;
import uab.eventos_backend.services.AuthService;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginUserEntity loginUserEntity) {
        return ResponseEntity.ok(this.authService.loginUser(loginUserEntity));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterUserEntity registerUserEntity) throws Exception {
        return ResponseEntity.ok(this.authService.registerUser(registerUserEntity));
    }

    @GetMapping("/currentUser")
    public UserEntity getCurrentUser(Principal principal) {
        return this.authService.getCurrentUser(principal);
    }
}
