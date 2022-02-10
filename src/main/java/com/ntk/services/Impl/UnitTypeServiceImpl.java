package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.UnitType;
import com.ntk.repositories.UnitTypeRepository;
import com.ntk.services.UnitTypeService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitTypeServiceImpl implements UnitTypeService {
    @Autowired
    private UnitTypeRepository unitTypeRepository;

    @Override
    @Transactional
    public JSONObject getUnitType(int unitTypeId, String... params) {
        UnitType unitType = unitTypeRepository.getUnitType(unitTypeId, params);
        if (unitType == null)
            return null;
        JSONObject unitTypeJsonObject = new JSONObject();
        unitTypeJsonObject.put("unitTypeId", UtilsController.encodeBase64(String.valueOf(unitType.getUnitTypeId())));
        unitTypeJsonObject.put("unitTypeName", unitType.getName());
        List<JSONObject> units = new ArrayList<>();
        unitType.getUnits().forEach(e -> {
            JSONObject unit = new JSONObject();
            unit.put("unitId", UtilsController.encodeBase64(String.valueOf(e.getUnitId())));
            unit.put("unitName", e.getName());
            units.add(unit);
        });
        unitTypeJsonObject.put("units", units);

        return unitTypeJsonObject;
    }

    @Override
    @Transactional
    public List<JSONObject> getUnitTypes() {
        List<UnitType> unitTypes = unitTypeRepository.getUnitTypes();
        if(unitTypes==null)
            return null;
        List<JSONObject> jsonObjects = new ArrayList<>();
        unitTypes.forEach(e->{
            JSONObject unitType = new JSONObject();
            unitType.put("unitTypeId", UtilsController.encodeBase64(String.valueOf(e.getUnitTypeId())));
            unitType.put("unitTypeName", e.getName());
            jsonObjects.add(unitType);
        });
        return jsonObjects;

    }
}
