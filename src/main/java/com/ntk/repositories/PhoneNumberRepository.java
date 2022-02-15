package com.ntk.repositories;

import com.ntk.pojos.PhoneNumber;


public interface PhoneNumberRepository {
    boolean addPhoneNumber(PhoneNumber phoneNumber);
    boolean updatePhoneNumber(PhoneNumber phoneNumber);
    boolean deletePhoneNumber(PhoneNumber phoneNumber);
    PhoneNumber getPhoneNumber(int phoneNumberId);
}
