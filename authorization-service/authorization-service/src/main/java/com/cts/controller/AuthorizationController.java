package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.client.UserClient;
import com.cts.configuration.JWTUtility;
import com.cts.entity.Customer;
import com.cts.entity.JwtRequest;
import com.cts.entity.JwtResponse;
import com.cts.exception.UserAlreadyExistException;
import com.cts.exception.UserNotFoundException;
import com.cts.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private UserClient userClient;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			} catch (DisabledException   e) {
				throw new DisabledException("User Disabled");
			}catch (BadCredentialsException e) {
				
				throw new BadCredentialsException("Bad Credential");
			}catch (Exception e) {
			
				throw new BadCredentialsException("Bad Credential");			}
		final UserDetails userDetails=authorizationService.loadUserByUsername(authenticationRequest.getUsername());
		final String token=jwtUtility.generateToken(userDetails);
		final String customerName=authorizationService.getUser(authenticationRequest.getUsername()).getName();
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token,customerName),HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) throws Exception{
		try {
		authorizationService.getCustomer(customer.getUsername());
		throw new UserAlreadyExistException("User Already Exist");
		}catch (UserNotFoundException e) {
//			log.info("Inside controller ->createUser UserNotFoundException {}",e);
		}
		authorizationService.save(customer);
		userClient.addUser(customer);
		return new ResponseEntity<String>("User Created Successfully",HttpStatus.OK);
		
		
	}
	
	@GetMapping("/authorize")
	public boolean authorizeRequest(@RequestHeader(value = "Authorization", required = true) String requestToken) throws Exception {
		String token=null;
		if(requestToken!=null&& requestToken.startsWith("Bearer ")) {
			token=requestToken.substring(7);
			
			if(!jwtUtility.isTokenExpired(token))
				return true;
		}
		return false;
	}
	
}
