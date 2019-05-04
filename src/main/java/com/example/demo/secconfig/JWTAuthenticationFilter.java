/**
 * 
 */
package com.example.demo.secconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Smail GHATRIF
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	 public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authenticationManager.authenticate(authentication);
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			
			// get User from inputStream and cast it by jakson to AppUser object 
			
			AppUser appUser = new ObjectMapper().readValue( request.getInputStream() ,AppUser.class);
			System.out.println( appUser );
			// Authenticate the user by authenticationManager and return the result authentication
			return authenticationManager.authenticate( 
					new UsernamePasswordAuthenticationToken(appUser.getLogin(), appUser.getPassword()));
		}catch( Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// get User form authentication object ( spring security user org.springframework.security.core.userdetails.User )
		User user = (User) authResult.getPrincipal();
		// get User roles from authentication result and store them in arrayList of String
		List<String> authorities = new ArrayList<>();
		authResult.getAuthorities().forEach(a -> {
			authorities.add( a.getAuthority());
		});
		
		String jwt = JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())
				.withArrayClaim("roles", authorities.toArray(new String[ authorities.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis()+ 3600*24*100))
				.sign(Algorithm.HMAC256(SecurityConstants.SECRET.getValue()));
		
		response.setHeader(SecurityConstants.AUTHORIZATION.getValue(), SecurityConstants.TOKENPREFIX.getValue() + jwt);
	}

	
	

}
