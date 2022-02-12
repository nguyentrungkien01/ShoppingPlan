package com.ntk.services;

import com.ntk.pojos.User;
import com.ntk.pojos.UserProduct;
import com.ntk.pojos.UserProductId;

import java.util.List;

public interface UserProductService {
    boolean deleteUserProduct(UserProduct userProduct);
    boolean addUserProduct(UserProduct userProduct);
    List<UserProduct> getUserProducts(UserProductId userProductId, String params);
    List<UserProduct> getUserProducts(User user, String... params);

}
