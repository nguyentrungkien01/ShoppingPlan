package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Stall;
import com.ntk.pojos.User;
import com.ntk.repositories.AccountReportRepository;
import com.ntk.repositories.UserRepository;
import com.ntk.services.AccountReportService;
import com.ntk.services.AccountService;
import com.ntk.services.StallService;
import com.ntk.services.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StallService stallService;

    @Autowired
    private AccountReportRepository accountReportRepository;

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
    @Transactional
    public User getUserObj(int userId, String... params) {
        return userRepository.getUser(userId, params);
    }

    @Override
    @Transactional
    public JSONObject getCurrentUserDetail() {
        User user = getUserObj(getCurrentUser().getUserId(), "account", "stalls");
        Set<String> locations = new HashSet<>();
        user.getLocations().forEach(e->locations.add(e.getName()));

        Set<Stall> stalls =user.getStalls();
        AtomicInteger amountProduct = new AtomicInteger();
       stalls.forEach(e-> amountProduct.addAndGet(stallService.getStall(e.getStallId(), "products")
               .getProducts().size()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amountStall", user.getStalls().size());
        jsonObject.put("amountProduct", amountProduct.get());
        jsonObject.put("dateJoined", new SimpleDateFormat("dd-MM-yyy").format( user.getAccount().getDateCreated()));
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("idCard", user.getIdCard());
        jsonObject.put("sex", user.getSex()?"Nam":"Nữ");
        jsonObject.put("locations", locations);
        jsonObject.put("facebookLink", user.getFacebookLink());
        jsonObject.put("dateOfBirth", new SimpleDateFormat("dd-MM-yyyy").format( user.getDateOfBirth()));
        jsonObject.put("amountReport", accountReportRepository.countAccountReport(user.getAccount()));
        return jsonObject;
    }

    @Override
    @Transactional
    public JSONObject getCurrentUserPhoneNumber() {
        User user = getUserObj(getCurrentUser().getUserId(), "phoneNumbers");
        Set<JSONObject> phoneNumbers = new HashSet<>();
        user.getPhoneNumbers().forEach(e->{
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("name", e.getPhoneNumber());
            jsonObject.put("id", UtilsController.encodeBase64(String.valueOf(e.getPhoneNumberId())));
            phoneNumbers.add(jsonObject);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNumbers", phoneNumbers);
        return jsonObject;
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
