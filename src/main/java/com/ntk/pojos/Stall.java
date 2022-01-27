package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name= "Stall")
public class Stall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stall_id")
    private int stallId;

    @Column(name ="name", length = 60, nullable = false)
    private String name;

    @Column(name ="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "stall")
    private Set<StallProduct> stallProducts;

    public int getStallId() {
        return stallId;
    }

    public void setStallId(int stallId) {
        this.stallId = stallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<StallProduct> getStallProducts() {
        return stallProducts;
    }

    public void setStallProducts(Set<StallProduct> stallProducts) {
        this.stallProducts = stallProducts;
    }
}
