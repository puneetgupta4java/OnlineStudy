package com.adda.security.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.adda.security.token.JwtTokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class JwtTokenFilter implements Filter {

	private JwtTokenProvider jwtTokenProvider;

	private static final Logger LOGGER = LogManager.getLogger(JwtTokenFilter.class);

	@Autowired
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Request received for the URL " + ((HttpServletRequest) request).getRequestURL().toString());
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		if (token != null) {
			if (jwtTokenProvider.validateToken(token)) {
				Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
				SecurityContextHolder.getContext().setAuthentication(auth);
				LOGGER.info("Request authenticated successfully");
			} else {
				LOGGER.info("Request  authentication failed");
				createResponse(response);
			}
		}
		LOGGER.info("Request proceed further using filterchain.dofilter");
		chain.doFilter(request, response);
	}

	private void createResponse(ServletResponse servletResponse) throws JsonProcessingException, IOException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setHeader("Content-Type", "application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		Map<String, String> responseContent = new HashMap<>();
		responseContent.put("message", "Invalid token for authorization");
		response.getOutputStream();
	}
}
