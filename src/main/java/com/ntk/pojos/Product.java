package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name ="Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_id")
    private int productId;

    @Column(name ="name", length = 30, nullable = false, columnDefinition = "nvarchar")
    private String name;

    @Column(name="image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<StallProduct> stallProducts;

    @OneToMany(mappedBy = "product")
    private Set<UserProduct> userProducts;

    @OneToMany(mappedBy = "product")
    private Set<ProductUnit> productUnits;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<StallProduct> getStallProducts() {
        return stallProducts;
    }

    public void setStallProducts(Set<StallProduct> stallProducts) {
        this.stallProducts = stallProducts;
    }

    public Set<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(Set<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }

    public Set<ProductUnit> getProductUnits() {
        return productUnits;
    }

    public void setProductUnits(Set<ProductUnit> productUnits) {
        this.productUnits = productUnits;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
