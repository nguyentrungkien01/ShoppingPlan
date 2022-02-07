package com.ntk.services;

import com.ntk.pojos.User;
import org.json.simple.JSONObject;

public interface UserService {
    boolean addUser(User user);
    boolean deleteUser(User user);
    JSONObject getUser(int userId);
    boolean updateUser(User user);
    User getUser(String idCard);
    User getCurrentUser();
    User getUserObj(int userId, String... params);
}
