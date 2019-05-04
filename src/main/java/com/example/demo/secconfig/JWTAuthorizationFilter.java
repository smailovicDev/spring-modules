package com.example.demo.secconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.addHeader("Access-Control-Allow-Headers", 
        		"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization, Error");
		
		if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else if(request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        else {
        	
        	// get the token form request header 
            String jwtToken = request.getHeader(SecurityConstants.AUTHORIZATION.getValue());
            
            System.out.println(" first "+ request.getHeader("Accept")+ " Second  "+request.getHeader("Content-Type") );
            
            // check if the token start with our key if not it's not a good token 
            if ( jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKENPREFIX.getValue() )) {
            	
            	// redirect the user to authentication page and return
                filterChain.doFilter(request, response);
                return;
            }
            
            // remove the prefix from our token 
            String jwt = jwtToken.substring(SecurityConstants.TOKENPREFIX.getValue().length());
            
            System.out.println(jwtToken);
            
            // prepare the JWTVerifier set the key and algorithm
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getValue())).build();
            
            // decode the token
            DecodedJWT decodedJWT = verifier.verify(jwt);
            
            // get the username from the tocken 
            String username = decodedJWT.getSubject();
            
            // get Roles as list 
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
            
            // create granted authority from roles list
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
            // create user from user name and authorities 
            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            // set the user to the security context 
            SecurityContextHolder.getContext().setAuthentication(user);
            
            filterChain.doFilter(request, response);
            
        }
		
	}

}
