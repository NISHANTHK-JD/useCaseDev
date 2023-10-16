package com.cts.filter;


import javax.security.sasl.AuthenticationException;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Slf4j
@Component
public class TokenValidationFilter implements GatewayFilter{
	
    WebClient client = WebClient.create("http://localhost:8080/authorization");
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request=exchange.getRequest();
		String jwt=request.getHeaders()
				.getOrEmpty("Authorization")
				.get(0)
				.substring(7);
		return client
				.get()
				.uri("/authorize")
				.header("Authorization","Bearer "+jwt)
				.retrieve()
				.onStatus(HttpStatus.BAD_REQUEST::equals, cr->Mono.error(new AuthenticationException("Bad token")))
				.onStatus(HttpStatus.REQUEST_TIMEOUT::equals, cr->Mono.error(new AuthenticationException("Login Expired")))				
				.bodyToMono(String.class)
				.then(chain.filter(exchange));
		
	}

}