package com.hospital.mgt.apigateway.security;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hospital.mgt.apigateway.config.JwtConfig;
import com.hospital.mgt.apigateway.exception.JwtTokenIncorrectStructureException;
import com.hospital.mgt.apigateway.exception.JwtTokenMalformedException;
import com.hospital.mgt.apigateway.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	@Autowired
	JwtConfig jwtConfig;
	
	@Value("${jwt.validity.refresh}")
	private long refreshValidity;
	
	@Value("${jwt.validity.access}")
	private long accessValidity;
	
	
	public String generateAccessToken(String id) {
		Claims claims = Jwts.claims().setSubject(id);
		long currentMills = System.currentTimeMillis();
		long expMills = currentMills + jwtConfig.getValidity() * 1000 * 60;
		Date exp = new Date(expMills);
		System.out.println("jwtConfig.getSecret() "+jwtConfig.getSecret());
	
		
		return Jwts.builder().setClaims(claims).setSubject(id).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + accessValidity * 1000 * 60))
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret()).compact();
	}
	
	public String generateRefreshToken(String id) {
		Claims claims = Jwts.claims().setSubject(id);
		long currentMills = System.currentTimeMillis();
		long expMills = currentMills + (refreshValidity * 1000 * 60);
		Date exp = new Date(expMills);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(currentMills))
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
				.compact();
	}
	
	public void validateToken(final String header) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			String[] sections = header.split(" ");
			if (sections.length != 2 || !"Bearer".equals(sections[0])) {
				throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure.");
			}
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfig.getSecret())).parseClaimsJws(sections[1]);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature.");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token.");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token.");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token.");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
	
	public String getUsernameOfTheToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtConfig.getSecret())
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
