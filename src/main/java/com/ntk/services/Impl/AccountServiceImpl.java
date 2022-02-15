package com.ntk.services.Impl;



import com.ntk.pojos.Account;
import com.ntk.pojos.Role;
import com.ntk.repositories.AccountReportRepository;
import com.ntk.repositories.AccountRepository;
import com.ntk.services.AccountService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public JSONObject getAccount(String username) {
        Account account = accountRepository.getAccount(username);
        if(account==null)
            return null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", account.getUsername());
        jsonObject.put("password", account.getPassword());
        jsonObject.put("isActive", account.getIsActive());
        return jsonObject;
    }

    @Override
    @Transactional
    public Account getAccountObj(String username) {
        return accountRepository.getAccount(username);
    }

    @Override
    @Transactional
    public Account getAccountObj(int accountId) {
        return accountRepository.getAccount(accountId);
    }

    @Override
    @Transactional
    public boolean addAccount(Account account) {
        byte active= 1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date now = new Date();
        account.setIsActive(active);
        try {
            account.setDateCreated(new SimpleDateFormat(
                    "yyyy/MM/dd HH:mm:ss").parse(formatter.format(now)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountRepository.addAccount(account);
    }

    @Override
    @Transactional
    public boolean updateAccount(Account account) {
        return accountRepository.updateAccount(account);
    }

    @Override
    @Transactional
    public boolean deleteAccount(Account account) {
        return accountRepository.deleteAccount(account);
    }

    @Override
    @Transactional
    public Role getRole(String name) {
        return accountRepository.getRole(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, IllegalAccessError{
        Account account  = accountRepository.getAccount(username);
        if(account==null)
            throw new UsernameNotFoundException("Tài khoản không tồn tại");

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), !(account.getIsActive()==0),
                true, true,!(account.getIsActive()==0), authorities);
    }
}
