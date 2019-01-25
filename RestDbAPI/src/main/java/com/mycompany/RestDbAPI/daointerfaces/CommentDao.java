package com.mycompany.RestDbAPI.daointerfaces;

import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.Comment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface CommentDao extends CrudRepository<Comment, Long>{
  
    List<Comment> findByArticle(Article article);
    
    void removeByArticleId(Long id);
}
