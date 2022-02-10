package com.ntk.controllers;

import com.ntk.pojos.Account;
import com.ntk.pojos.Role;
import com.ntk.pojos.User;
import com.ntk.services.AccountService;
import com.ntk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class IntroController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/")
    public String intro(){
        return "intro-page";
    }
    @RequestMapping(path = "/sign-in")
    public String signIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if(authentication==null || authentication instanceof AnonymousAuthenticationToken)
            return "sign-in";
        return "redirect:/homepage";
    }

    @GetMapping(path = "/sign-up")
    public String signUp(){
        return "sign-up";
    }


    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public String signUpData(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String username = httpServletRequest.getParameter("username");
        if(accountService.getAccount(username)!=null)
            return String.format("redirect:/sign-up/?error=%s",UtilsController.encodeBase64("existUsername"));

        String password = httpServletRequest.getParameter("password");
        String confirmPassword  =httpServletRequest.getParameter("password");

        if(!password.equals(confirmPassword))
            return String.format("redirect:/sign-up/?error=%s",UtilsController.encodeBase64("notMatchPassword"));

        String lastName = httpServletRequest.getParameter("lastName");
        String firstName= httpServletRequest.getParameter("firstName");
        String sex = httpServletRequest.getParameter("sex");
        String idCard = httpServletRequest.getParameter("idCard");
        if(userService.getUser(idCard)!=null)
            return String.format("redirect:/sign-up/?error=%s",UtilsController.encodeBase64("existIdCard"));

        String facebookLink =httpServletRequest.getParameter("facebookLink");
        Role role = accountService.getRole("client");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        boolean result = accountService.addAccount(account);
        if(result) {
            User user = new User();
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setSex(sex.contains("a"));
            user.setIdCard(idCard);
            user.setAccount(account);
            if(facebookLink.length()>0)
                user.setFacebookLink(facebookLink);
            result = userService.addUser(user);
        }else
            accountService.deleteAccount(accountService.getAccountObj(username));
       return String.format("redirect:/sign-up/?error=%s",
               result?UtilsController.encodeBase64("successful"):
                       UtilsController.encodeBase64("fail"));
    }
}

