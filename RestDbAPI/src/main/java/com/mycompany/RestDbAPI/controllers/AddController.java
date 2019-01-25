package com.mycompany.RestDbAPI.controllers;

import com.mycompany.RestDbAPI.controllers.info.Message;
import com.mycompany.RestDbAPI.daointerfaces.ArticleDao;
import com.mycompany.RestDbAPI.daointerfaces.CommentDao;
import com.mycompany.RestDbAPI.daointerfaces.UserDao;
import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.Comment;
import com.mycompany.RestDbAPI.model.User;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import com.mycompany.RestDbAPI.model.info.UserInfo;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/add")
@Transactional
public class AddController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/comment", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message addComment(@RequestBody CommentInfo comment) {
        Message message = new Message();
        try {
            Article article = articleDao.findById(comment.getArticle()).get();
            User user = userDao.findByLogin(comment.getAuthor());
            Comment newComment = new Comment();

            if (user == null) {
                message.setStatus(1);
                message.setDescription("No such user!");
                return message;
            }

            newComment.setArticle(article);
            newComment.setAuthor(user);
            newComment.setText(comment.getText());

            commentDao.save(newComment);
            
            message.setStatus(0);
        } catch (NoSuchElementException e) {
            message.setStatus(1);
            message.setDescription("No such article!");

            return message;
        }

        return message;
    }//end addComment

    @RequestMapping(value = "/article", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message addArticle(@RequestBody ArticleInfo article) {
        Message message = new Message();
        User user = userDao.findByLogin(article.getAuthor());

        if (user == null) {
            message.setStatus(1);
            message.setDescription("No such user!");
            return message;
        }

        Article newArticle = new Article();
        newArticle.setAuthor(user);
        newArticle.setName(article.getName());
        newArticle.setTopic(article.getTopic());
        newArticle.setText(article.getText());

        articleDao.save(newArticle);

        message.setStatus(0);
        
        return message;
    }//end add Article

    @RequestMapping(value = "/user", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message addUser(@RequestBody UserInfo user) {
        Message message = new Message();
        User newUser = userDao.findByLogin(user.getLogin());

        if (newUser != null) {
            message.setStatus(1);
            message.setDescription("This user has added aldedy!");
            return message;
        }

        newUser = new User();
        newUser.setLogin(user.getLogin());
        newUser.setPassword(user.getPassword());

        userDao.save(newUser);

        message.setStatus(0);
                
        return message;
    }//end add User

}//end Add Contriller
