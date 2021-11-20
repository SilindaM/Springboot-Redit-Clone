package com.example.demo.Services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Controller.LoginRequest;
import com.example.demo.Dto.AuthenticationResponse;
import com.example.demo.Dto.RegisterRequest;
import com.example.demo.Model.NotificationEmail;
import com.example.demo.Model.User;
import com.example.demo.Model.VerificationToken;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.VerificationTokenRepository;
import com.example.demo.exceptions.SpringRedditException;

import Security.JwtProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

	//we dont use @Autowired for other reasons
	private final PasswordEncoder passwordencoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	
	public void signup(RegisterRequest registerRequest) {
		User user=new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordencoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
		
		generateVerificationToken(user);
	}
	private String generateVerificationToken(User user) {
		//token generation
		String token =UUID.randomUUID().toString();
		mailService.sendMail(new NotificationEmail("Please activate your email ",user.getEmail(),"Thank you for signing up "+" please click the link to activate your account "+" http://localhost:8080/api/auth/accountVerification/" +token));
		//verificationToken object
		VerificationToken verificationToken=new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		
		//save the token
		verificationTokenRepository.save(verificationToken);
		
		return token;
	}
	public void verifyAccount(String token) {
	Optional<VerificationToken> verificationToken =	verificationTokenRepository.findByToken(token);
	verificationToken.orElseThrow(()->new SpringRedditException("invalid Token"));
	fetchUserAndEnable(verificationToken.get());
	} 
	//get the user and enable them
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) { 
		String username =verificationToken.getUser().getUsername();
		User user=userRepository.findByUsername(username).orElseThrow(()->new SpringRedditException("User not found "+username));
		user.setEnabled(true);
		userRepository.save(user);
	}
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token=jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(token,loginRequest.getUsername());
	}
}
