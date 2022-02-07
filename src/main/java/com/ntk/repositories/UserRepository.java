package com.ntk.repositories;

import com.ntk.pojos.User;

public interface UserRepository {
    boolean addUser(User user);
    boolean deleteUser(User user);
    User getUser(int userId, String... params);
    boolean updateUser(User user);
    User getUser(String idCard);
}
