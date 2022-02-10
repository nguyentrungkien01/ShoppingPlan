package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name ="Location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int locationId;

    @Column(name= "latitude", length = 60, nullable = false)
    private String latitude;

    @Column(name = "longitude", length = 60, nullable = false)
    private String longitude;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "nvarchar")
    private String name;

    @OneToMany(mappedBy = "location")
    private Set<Stall> stalls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Stall> getStalls() {
        return stalls;
    }

    public void setStalls(Set<Stall> stalls) {
        this.stalls = stalls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
