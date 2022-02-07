package com.ntk.repositories;

import com.ntk.pojos.Location;
import com.ntk.pojos.User;

import java.util.Set;

public interface LocationRepository {
    boolean addLocation(Location location);
    Location getLocation(int locationId);
}
