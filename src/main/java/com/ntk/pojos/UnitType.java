package com.ntk.pojos;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UnitType")
public class UnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_type_id")
    private int unitTypeId;

    @Column(name = "name", unique = true, length = 50, nullable = false, columnDefinition = "nvarchar")
    private String name;

    @OneToMany(mappedBy = "unitType")
    private Set<Unit> units;

    public int getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(int unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
