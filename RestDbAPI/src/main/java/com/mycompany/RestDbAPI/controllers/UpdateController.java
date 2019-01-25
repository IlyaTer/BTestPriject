package com.mycompany.RestDbAPI.controllers;

import com.mycompany.RestDbAPI.controllers.info.Message;
import com.mycompany.RestDbAPI.daointerfaces.ArticleDao;
import com.mycompany.RestDbAPI.daointerfaces.CommentDao;
import com.mycompany.RestDbAPI.daointerfaces.UserDao;
import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.Comment;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/update")
public class UpdateController {
    
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;
    
    @RequestMapping(value = "/comment", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateComment(@RequestBody CommentInfo comment) {
        Message message = new Message();
        try {
            Comment updateComment = commentDao.findById(comment.getId()).get();
            updateComment.setText(comment.getText());
            commentDao.save(updateComment);
            message.setStatus(0);
        } catch (NoSuchElementException e) {
            message.setStatus(1);
            message.setDescription("No such comment");
            return message;
        }
        return message;
    }//end updateComment
    
    @RequestMapping(value = "/article", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateArticle(@RequestBody ArticleInfo article) {
        Message message = new Message();
        try {
            Article updateArticle = articleDao.findById(article.getId()).get();
            updateArticle.setText(article.getText());
            articleDao.save(updateArticle);
            message.setStatus(0);
        } catch (NoSuchElementException e) {
            message.setStatus(1);
            message.setDescription("No such article");
            return message;
        }
        return message;
    }//end updateArticle
}
