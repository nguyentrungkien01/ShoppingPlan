package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="Stall_Product")
public class StallProduct implements Serializable {

    @EmbeddedId
    private StallProductId stallProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stall_id", insertable=false, updatable=false)
    private Stall stall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;

    public StallProductId getStallProductId() {
        return stallProductId;
    }

    public void setStallProductId(StallProductId stallProductId) {
        this.stallProductId = stallProductId;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
