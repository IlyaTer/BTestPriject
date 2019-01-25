package com.example.ArticleProxy.security.filters;

import com.example.ArticleProxy.security.filters.token.service.TokenAuthenticationService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) 
            throws IOException, ServletException {

        System.out.println("JWTAuthenticationFilter.doFilter");
        try {
            Authentication authentication = TokenAuthenticationService
                    .getAuthentication((HttpServletRequest) sr);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            fc.doFilter(sr, sr1);
        } catch (Exception e) {
            System.out.println("Parse Error!!!!!!!!!!!!!");
            ((HttpServletResponse) sr1).setStatus(403);
            ((HttpServletResponse) sr1).addHeader("Content-Type", 
                    "application/json");
            ((HttpServletResponse) sr1).getWriter().print("{\"message\":\"error!\"}");
            ((HttpServletResponse) sr1).getWriter().close();
        }
    }

}
