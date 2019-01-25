package com.example.ArticleProxy.security.filters;

import com.example.ArticleProxy.security.filters.token.service.TokenAuthenticationService;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{
    
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest hsr, 
            HttpServletResponse hsr1) throws AuthenticationException, IOException, ServletException {
        
        String username = hsr.getParameter("username");
        String password = hsr.getParameter("password");

        System.out.printf("JWTLoginFilter.attemptAuthentication: username %s", username);
        System.out.println();

        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(username,
                        password, Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("JWTLoginFilter.successfulAuthentication:");

        TokenAuthenticationService.addAuthentication(response, authResult.getName());

    }
    
    
    
    
}
