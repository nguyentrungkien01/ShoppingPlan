package com.ntk.services.Impl;

import com.ntk.pojos.PhoneNumber;
import com.ntk.repositories.PhoneNumberRepository;
import com.ntk.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Override
    @Transactional
    public boolean addPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.addPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public boolean updatePhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.updatePhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public boolean deletePhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.deletePhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public PhoneNumber getPhoneNumber(int phoneNumberId) {
        return phoneNumberRepository.getPhoneNumber(phoneNumberId);
    }
}
