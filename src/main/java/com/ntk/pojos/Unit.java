package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Unit")
public class Unit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "name", nullable = false, length = 20, columnDefinition = "nvarchar")
    private String name;

    @OneToMany(mappedBy = "unit")
    private Set<ProductUnit> productUnits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_type_id")
    private UnitType unitType;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<ProductUnit> getProductUnits() {
        return productUnits;
    }

    public void setProductUnits(Set<ProductUnit> productUnits) {
        this.productUnits = productUnits;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }
}
