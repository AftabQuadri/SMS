package com.student.management.filter;


import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.student.management.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
    private static final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
//	    String requestPath = request.getRequestURI();
//	    System.out.println("Inside jwt do filter");
//		if (requestPath.equals("/gettoken") || requestPath.equals("/users/register") || requestPath.startsWith("/v3/api-docs") || requestPath.startsWith("/swagger-ui")) {
//	        chain.doFilter(request, response);
//	        return;
//	    }
//		System.out.println("before authorization.getheader");
		String authorizationHeader = request.getHeader("Authorization");
		System.out.println(authorizationHeader);
		String username = null;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
//			System.out.println("JWT Token: " + jwt); // Debugging log
//			System.out.println("Extracted Username: " + username); // Debugging log

		} else {
		LOG.info("Authorization header is missing or does not start with Bearer ");

		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (jwtUtil.validateToken(jwt)) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
				LOG.info("Authentication successful for user: " + username); 
			} else {
				LOG.warn("JWT token validation failed for user: " + username); 

			}
		}else {
			LOG.warn("Username is null or the user is already authenticated");

		}
		chain.doFilter(request, response);
	}
}