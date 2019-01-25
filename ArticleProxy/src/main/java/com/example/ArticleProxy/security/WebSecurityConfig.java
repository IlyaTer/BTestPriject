package com.example.ArticleProxy.security;

import com.example.ArticleProxy.security.filters.JWTAuthenticationFilter;
import com.example.ArticleProxy.security.filters.JWTLoginFilter;
import com.example.ArticleProxy.user.details.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.cors().configure(http);
        
        http.authorizeRequests().antMatchers("/","/art/get/article").permitAll();
        
        http.authorizeRequests().anyRequest().authenticated()
         .and()
         .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), 
                        UsernamePasswordAuthenticationFilter.class)
         .addFilterBefore(new JWTAuthenticationFilter(),
                 UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

}
