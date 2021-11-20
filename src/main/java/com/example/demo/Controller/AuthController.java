package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthenticationResponse;
import com.example.demo.Dto.RegisterRequest;
import com.example.demo.Services.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth/")
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registered",HttpStatus.OK);
	}
	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token);
		 return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
    }
	@PostMapping("login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
