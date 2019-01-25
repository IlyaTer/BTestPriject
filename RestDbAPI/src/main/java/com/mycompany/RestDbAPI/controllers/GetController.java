package com.mycompany.RestDbAPI.controllers;

import com.mycompany.RestDbAPI.daointerfaces.ArticleDao;
import com.mycompany.RestDbAPI.daointerfaces.CommentDao;
import com.mycompany.RestDbAPI.daointerfaces.UserDao;
import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.Comment;
import com.mycompany.RestDbAPI.model.User;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import com.mycompany.RestDbAPI.model.info.UserInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/get")
@Transactional
public class GetController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/user", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo getUser(@RequestBody UserInfo user) {
        User userRes = userDao.findByLogin(user.getLogin());
        UserInfo userInfo = new UserInfo();
        if (userRes != null) {
            if (userRes.getPassword().equals(user.getPassword())) {
                userInfo.setLogin(userRes.getLogin());
                userInfo.setId(userRes.getId());
            } else {
                userInfo.setPassword("Ilegall password!");
            }
        }

        return userInfo;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleInfo> getArticles() {
        List<ArticleInfo> resList = new LinkedList<>();

        for (Article art : articleDao.findAll()) {
            resList.add(new ArticleInfo(art.getId(), art.getName(),
                    art.getTopic(), null,
                    art.getAuthor().getLogin(), null));
        }

        return resList;
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleInfo getArticlesById(@PathVariable("id") Long id) {
        List<CommentInfo> comments = new LinkedList<>();
        ArticleInfo resArt = new ArticleInfo();

        try {
            Article art = articleDao.findById(id).get();

            for (Comment c : commentDao.findByArticle(art)) {
                comments.add(new CommentInfo(c.getId(), c.getArticle().getId(),
                        c.getAuthor().getLogin(), c.getText()));
            }
            resArt.setId(id);
            resArt.setAuthor(art.getAuthor().getLogin());
            resArt.setName(art.getName());
            resArt.setTopic(art.getTopic());
            resArt.setText(art.getText());
            resArt.setComments(comments);

            return resArt;
        } catch (NoSuchElementException e) {
            return resArt;
        }
    }

    //to add other methods
}
