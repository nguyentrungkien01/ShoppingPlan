package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name ="Product_Unit")
public class ProductUnit implements Serializable {
    @EmbeddedId
    private ProductUnitId productUnitId;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id" , insertable=false, updatable=false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id" , insertable=false, updatable=false)
    private Unit unit;

    public ProductUnitId getProductUnitId() {
        return productUnitId;
    }

    public void setProductUnitId(ProductUnitId productUnitId) {
        this.productUnitId = productUnitId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
