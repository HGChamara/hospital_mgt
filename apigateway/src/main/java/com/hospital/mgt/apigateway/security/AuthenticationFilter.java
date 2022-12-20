package com.hospital.mgt.apigateway.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.hospital.mgt.apigateway.config.JwtConfig;
import com.hospital.mgt.apigateway.model.ErrorResponseDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RefreshScope
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	private final RouterValidator routerValidator;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtConfig jwtConfig;
	
	public AuthenticationFilter(RouterValidator routerValidator, JwtTokenUtil jwtTokenUtil, JwtConfig jwtConfig) {
		super(Config.class);
		this.routerValidator = routerValidator;
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtConfig = jwtConfig;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if(routerValidator.isSecured.test(exchange.getRequest()) && !jwtConfig.isAuthDisabled()) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing authorization header.");
				}
				
				String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0));
				try {
					jwtTokenUtil.validateToken(authHeader);
				} catch(Exception e) {
					log.error("Error validationg authentication header",e);
					List<String> details = new ArrayList<String>();
					details.add(e.getLocalizedMessage());
					ErrorResponseDto error = new ErrorResponseDto(new Date(), 
																	HttpStatus.UNAUTHORIZED.value(), 
																	"UNAUTHORIZED",
																	details, 
																	exchange.getRequest().getURI().toString());
					ServerHttpResponse response = exchange.getResponse();
					
					byte[] bytes = SerializationUtils.serialize(error);
					
					DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
					response.writeWith(Flux.just(buffer));
					response.setStatusCode(HttpStatus.UNAUTHORIZED);
					return response.setComplete();
				}
				
			}
			return chain.filter(exchange);
		});
	}
	
	public static class Config {
		
	}

}


