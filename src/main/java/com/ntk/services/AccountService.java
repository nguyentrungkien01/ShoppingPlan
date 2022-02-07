package com.ntk.services;

import com.ntk.pojos.Account;
import com.ntk.pojos.Role;
import org.json.simple.JSONObject;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    JSONObject getAccount(String username);
    Account getAccountObj(String username);
    boolean addAccount(Account user);
    boolean updateAccount(Account user);
    boolean deleteAccount(Account user);
    Role getRole (String name);
}
