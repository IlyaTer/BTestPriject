package com.mycompany.RestDbAPI.controllers;

import com.mycompany.RestDbAPI.controllers.info.Message;
import com.mycompany.RestDbAPI.daointerfaces.ArticleDao;
import com.mycompany.RestDbAPI.daointerfaces.CommentDao;
import com.mycompany.RestDbAPI.daointerfaces.UserDao;
import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.Comment;
import com.mycompany.RestDbAPI.model.User;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/delete")
@Transactional
public class DeleteController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message delComment(@PathVariable("id") Long id) {
        Message message = new Message();
        try {
            Comment delComment = commentDao.findById(id).get();
            commentDao.delete(delComment);
            message.setStatus(0);
        } catch (NoSuchElementException e) {
            message.setStatus(1);
            message.setDescription("No such comment");
            return message;
        }
        return message;
    }//end delComment
    
    @RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message delArticle(@PathVariable("id") Long id) {
        Message message = new Message();
        try {
            Article article = articleDao.findById(id).get();
            articleDao.delete(article);
            message.setStatus(0);
        } catch (NoSuchElementException e) {
            message.setStatus(1);
            message.setDescription("No such article");
            return message;
        }
        return message;
    }//end delArticle
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message delUser(@PathVariable("id") Long id) {
        Message message = new Message();
        //add logic
        return message;
    }

}
