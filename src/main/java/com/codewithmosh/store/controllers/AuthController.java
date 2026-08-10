package com.codewithmosh.store.controllers;
import com.codewithmosh.store.dtos.JwtResponse;
import com.codewithmosh.store.dtos.LoginUser;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import com.codewithmosh.store.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController()
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginUser request){
       //automatically does the encoder.mathces check as the authenticationManager provider  have provider.setPasswordEncoder(passwordEncoder());
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
       );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader) {
        var token = authHeader.replace("Bearer ", "");

        return jwtService.validateToken(token);
    }

}
