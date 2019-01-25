package com.example.ArticleProxy.model.user.dao;

import com.example.ArticleProxy.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
