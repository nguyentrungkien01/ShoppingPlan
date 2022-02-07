package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Account;
import com.ntk.pojos.User;
import com.ntk.repositories.UserRepository;
import com.ntk.services.AccountService;
import com.ntk.services.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(User user) {
        return userRepository.deleteUser(user);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public User getUser(String idCard) {
        return userRepository.getUser(idCard);
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        return accountService.getAccountObj(UtilsController.getCurrentUsername()).getUser();
    }

    @Override
    public User getUserObj(int userId, String... params) {
        return userRepository.getUser(userId, params);
    }

    @Override
    @Transactional
    public JSONObject getUser(int userId) {
        User user = userRepository.getUser(userId);
        if(user==null)
            return null;
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("facebookLink", user.getFacebookLink());
        jsonObject.put("idCard", user.getIdCard());
        jsonObject.put("sex", user.getSex());
        jsonObject.put("phoneNumbers", user.getPhoneNumbers());
        return jsonObject;
    }
}
