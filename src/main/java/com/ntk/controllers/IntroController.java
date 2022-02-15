package com.ntk.controllers;

import com.ntk.pojos.Account;
import com.ntk.pojos.PhoneNumber;
import com.ntk.pojos.Role;
import com.ntk.pojos.User;
import com.ntk.services.AccountService;
import com.ntk.services.PhoneNumberService;
import com.ntk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IntroController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @RequestMapping(path = "/")
    public String intro(){
        return "intro-page";
    }

    @RequestMapping(path = "/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping(path = "/sign-up")
    public String signUp(){
        return "sign-up";
    }


    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public String signUpData(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");

        String username = httpServletRequest.getParameter("username");
        String dateOfBirth = httpServletRequest.getParameter("dateOfBirth");
        if(accountService.getAccount(username)!=null)
            return String.format("redirect:/sign-up/?error=%s",UtilsController.encodeBase64("existUsername"));

        String password = httpServletRequest.getParameter("password");
        String confirmPassword  =httpServletRequest.getParameter("confirmPassword");

        if(!password.equals(confirmPassword))
            return String.format("redirect:/sign-up/?error=%s",UtilsController.encodeBase64("notMatchPassword"));

        String lastName = httpServletRequest.getParameter("lastName");
        String firstName= httpServletRequest.getParameter("firstName");
        String sex = httpServletRequest.getParameter("sex");
        String idCard = httpServletRequest.getParameter("idCard");
        String phoneNumber = httpServletRequest.getParameter("phoneNumber");
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
            user.setSex(sex.equals("male"));
            user.setIdCard(idCard);
            user.setAccount(account);
            try {
                Date dob = new SimpleDateFormat(
                        "yyyy-MM-dd").parse(dateOfBirth);
                user.setDateOfBirth(dob);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(facebookLink==null || facebookLink.length()>0)
                user.setFacebookLink(facebookLink);
            else
                user.setFacebookLink(null);
            result = userService.addUser(user);
            PhoneNumber pNumber = new PhoneNumber();
            pNumber.setPhoneNumber(phoneNumber);
            pNumber.setUser(user);
            phoneNumberService.addPhoneNumber(pNumber);
        }else
            accountService.deleteAccount(accountService.getAccountObj(username));
       return String.format("redirect:/sign-up/?error=%s",
               result?UtilsController.encodeBase64("successful"):
                       UtilsController.encodeBase64("fail"));
    }
}

