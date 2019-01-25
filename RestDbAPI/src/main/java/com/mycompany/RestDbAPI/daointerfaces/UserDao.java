package com.mycompany.RestDbAPI.daointerfaces;

import com.mycompany.RestDbAPI.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long>{
   
    User findByLogin(String login);
}
