package com.cts.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.service.AuthorizationService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthorizationService authorizationService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		log.info("Inside JWTRequestFilter -> doFilterInternal");
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String username=null;
		String password=null;
		String token=null;
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
			token=requestTokenHeader.substring(7);
//			log.info("Inside JWTRequestFilter -> doFilterInternal HAS TOKEN {}",token);
			try {
				username=jwtUtility.getUsernameFromToken(token);
			}catch (IllegalArgumentException e) {
//				log.info("Inside JWTRequestFilter -> IllegalArgumentException {}",e);

				 System.out.println(e);
			}catch (ExpiredJwtException e) {
//				log.info("Inside JWTRequestFilter -> ExpiredJwtException {}",e);

			}catch (Exception e) {
//				log.info("Inside JWTRequestFilter -> Exception {}",e);
			}
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=authorizationService.loadUserByUsername(username);
			if(jwtUtility.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities())	;
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);;
			}
			
		}
		filterChain.doFilter(request, response);
		
	}
	
	
	
}
