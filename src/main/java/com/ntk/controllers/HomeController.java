package com.ntk.controllers;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Objects;


@Controller
public class HomeController {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @RequestMapping(path = "/")
    @Transactional
    public String index(Model model){
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        Query q = s.createQuery("From Category");
        model.addAttribute("categories", q.getResultList());
        return "base";
    }
}
