package com.ntk.controllers;

import com.ntk.pojos.Account;
import com.ntk.pojos.PhoneNumber;
import com.ntk.pojos.User;
import com.ntk.services.AccountService;
import com.ntk.services.PhoneNumberService;
import com.ntk.services.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(path = "/rule")
    public String viewRule() {
        return "rule";
    }

    @RequestMapping(path = "/change-password")
    public String changePassword() {
        return "change-password";
    }

    @RequestMapping(path = "/change-password", method = RequestMethod.POST)
    public String changePasswordData(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String password = httpServletRequest.getParameter("oldPassword");
        String newPassword = httpServletRequest.getParameter("newPassword");
        String confirmPassword = httpServletRequest.getParameter("confirmPassword");
        Account account = accountService.getAccountObj(UtilsController.getCurrentUsername());

        if(!passwordEncoder.matches(password, account.getPassword()))
            return String.format("redirect:change-password/?&error=%s",
                    UtilsController.encodeBase64("passwordNotExist"));

        if(!newPassword.equals(confirmPassword))
            return String.format("redirect:change-password/?&error=%s",
                    UtilsController.encodeBase64("confirmNotMatch"));

        account.setPassword(passwordEncoder.encode(password));
        boolean result = accountService.updateAccount(account);
        return String.format("redirect:change-password/?&error=%s",
                result ? UtilsController.encodeBase64("successful") :
                        UtilsController.encodeBase64("fail"));
    }

    @RequestMapping(path = "/info")
    public String info(Model model) {
        model.addAttribute("info",userService.getCurrentUserDetail());
        return "info";
    }

    @GetMapping( "/info/api/phoneNumberData")
    public ResponseEntity<JSONObject> getPhoneNumber(){
        return new ResponseEntity<>(userService.getCurrentUserPhoneNumber(), HttpStatus.OK);
    }

    @PostMapping( "/info/api/addPhoneNumber")
    public ResponseEntity<Boolean> addPhoneNumber(
            @RequestBody Map<String, String> params){
        String phoneNumber = params.get("phoneNumber");
        User user = userService.getCurrentUser();
        PhoneNumber pNumber = new PhoneNumber();
        pNumber.setPhoneNumber(phoneNumber);
        pNumber.setUser(user);
        return new ResponseEntity<>(
                phoneNumberService.addPhoneNumber(pNumber),
                HttpStatus.OK);
    }
    @PostMapping( "/info/api/editPhoneNumber")
    public ResponseEntity<Boolean> editPhoneNumber(
            @RequestBody Map<String, String> params){
        String phoneNumberId = params.get("phoneNumberId");
        String phoneNumber = params.get("phoneNumberName");
        PhoneNumber pNumber = phoneNumberService.getPhoneNumber(
                Integer.parseInt(UtilsController.decodeBase64(phoneNumberId)));
        pNumber.setPhoneNumber(phoneNumber);
        return new ResponseEntity<>(
                phoneNumberService.updatePhoneNumber(pNumber),
                HttpStatus.OK);
    }
    @PostMapping("/info/api/deletePhoneNumber")
    public ResponseEntity<Boolean> deletePhoneNumber(
            @RequestBody Map<String, String> params){
        String phoneNumberId = params.get("phoneNumberId");
        PhoneNumber pNumber = phoneNumberService.getPhoneNumber(
                Integer.parseInt(UtilsController.decodeBase64(phoneNumberId)));
        return new ResponseEntity<>(
                phoneNumberService.deletePhoneNumber(pNumber),
                HttpStatus.OK);
    }
}
