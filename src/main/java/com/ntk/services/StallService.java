package com.ntk.services;

import com.ntk.pojos.Stall;
import org.json.simple.JSONObject;

import java.util.List;

public interface StallService {
    List<JSONObject> getStallInfo(int... params);
    JSONObject getStallAmount();
    JSONObject getProductListInfo(int stallId, int... params);
    JSONObject getStallLocation(int stallId);
    JSONObject getStallName(int stallId);
    JSONObject getStallProductListAmount(int stallId);
    boolean addStall(Stall stall);
    boolean updateStall(Stall stall);
    boolean deleteStall(Stall stall);
    Stall getStall(int stallId, String... params);
    JSONObject getStallDetail(int stallId);
}
