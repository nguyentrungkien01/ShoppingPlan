package com.ntk.services;

import com.ntk.pojos.PhoneNumber;

public interface PhoneNumberService {
    boolean addPhoneNumber(PhoneNumber phoneNumber);
    boolean updatePhoneNumber(PhoneNumber phoneNumber);
    boolean deletePhoneNumber(PhoneNumber phoneNumber);
    PhoneNumber getPhoneNumber(int phoneNumberId);
}
