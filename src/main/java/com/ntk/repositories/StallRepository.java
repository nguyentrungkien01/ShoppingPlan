package com.ntk.repositories;

import com.ntk.pojos.*;

import java.util.List;

public interface StallRepository {
    List<Stall> getStallInfo(int... params);
    int getStallAmount();
    Location getStallLocation(int stallId);
    List<Product> getProductList(int stallId, int... params);
    List<ProductUnit> getProductUnitList(int productId);
    String getStallName(int stallId);
    int getStallProductListAmount(int stallId);
    boolean addStall(Stall stall);
    boolean updateStall(Stall stall);
    boolean deleteStall(Stall stall);
    Stall getStall(int stallId, String... params);
}
