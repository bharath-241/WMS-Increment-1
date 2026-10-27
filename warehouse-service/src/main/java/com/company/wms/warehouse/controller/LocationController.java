
package com.company.wms.warehouse.controller;

import com.company.wms.warehouse.location.Location;
import com.company.wms.warehouse.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location createLocation(
            @Valid @RequestBody Location location) {

        return locationService.createLocation(location);
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{id}")
    public Location updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody Location location) {

        return locationService.updateLocation(id, location);
    }
}
