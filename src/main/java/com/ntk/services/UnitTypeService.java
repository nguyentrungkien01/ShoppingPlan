package com.ntk.services;

import org.json.simple.JSONObject;

import java.util.List;

public interface UnitTypeService {
    JSONObject getUnitType(int unitTypeId, String... params);
    List<JSONObject> getUnitTypes();
}
