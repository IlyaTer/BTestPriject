package com.mycompany.RestDbAPI.daointerfaces;

import com.mycompany.RestDbAPI.model.Article;
import org.springframework.data.repository.CrudRepository;


public interface ArticleDao extends CrudRepository<Article, Long>{
   
}
