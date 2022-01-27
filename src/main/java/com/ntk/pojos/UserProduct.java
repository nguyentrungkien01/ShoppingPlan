package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name ="User_Product")
public class UserProduct implements Serializable {
    @EmbeddedId
    private UserProductId userProductId;

    @Column(name = "amount")
    private int amount;

    @Column(name ="time_ordered")
    private Date timeOrdered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;

    public UserProductId getUserProductId() {
        return userProductId;
    }

    public void setUserProductId(UserProductId userProductId) {
        this.userProductId = userProductId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(Date timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
