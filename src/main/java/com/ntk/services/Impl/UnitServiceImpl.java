package com.ntk.services.Impl;

import com.ntk.pojos.Unit;
import com.ntk.repositories.UnitRepository;
import com.ntk.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Override
    @Transactional
    public Unit getUnit(int unitId) {
        return unitRepository.getUnit(unitId);
    }
}
