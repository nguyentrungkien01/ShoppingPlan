package com.ntk.repositories;

import com.ntk.pojos.UnitType;

import java.util.List;

public interface UnitTypeRepository {
    UnitType getUnitType(int unitTypeId, String... params);
    List<UnitType> getUnitTypes();
}
