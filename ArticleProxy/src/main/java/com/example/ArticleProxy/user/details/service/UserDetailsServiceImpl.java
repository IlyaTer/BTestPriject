package com.example.ArticleProxy.user.details.service;

import com.example.ArticleProxy.model.user.User;
import com.example.ArticleProxy.model.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        
        User userRes = userDao.findByLogin(string);
        
        if (userRes != null) {
            UserDetails userDetails = (UserDetails) org.springframework.security.core.userdetails.User
                    .withUsername(userRes.getLogin()).
                    password(this.passwordEncoder.encode(userRes.getPassword())).
                    roles("USER").build();

            return userDetails;
        }
        
        return null;
    }
    
}
