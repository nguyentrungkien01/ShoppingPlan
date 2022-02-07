package com.ntk.repositories;

import com.ntk.pojos.Account;
import com.ntk.pojos.Role;

public interface AccountRepository {
    Account getAccount(String username);
    boolean addAccount(Account user);
    boolean updateAccount(Account user);
    boolean deleteAccount(Account user);
    Role getRole(String name);
}
