
package com.company.wms.warehouse.service;

import com.company.wms.warehouse.exception.LocationNotFoundException;
import com.company.wms.warehouse.location.Location;
import com.company.wms.warehouse.repository.LocationRepository;
import com.company.wms.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final WarehouseRepository warehouseRepository;

    public LocationService(
            LocationRepository locationRepository,
            WarehouseRepository warehouseRepository) {
        this.locationRepository = locationRepository;
        this.warehouseRepository = warehouseRepository;
    }

    // Validate that the Warehouse exists
    private void validateWarehouse(Long warehouseId) {
        if (warehouseId == null
                || !warehouseRepository.existsById(warehouseId)) {
            throw new IllegalArgumentException(
                    "Warehouse not found with id: " + warehouseId);
        }
    }

    // Create a new location
    public Location createLocation(Location location) {
        validateWarehouse(location.getWarehouseId());

        LocalDateTime now = LocalDateTime.now();
        location.setCreatedAt(now);
        location.setUpdatedAt(now);

        return locationRepository.save(location);
    }

    // Get a location by ID
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(
                        "Location not found with id: " + id));
    }

    // Get all locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Update an existing location
    public Location updateLocation(Long id, Location location) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(
                        "Location not found with id: " + id));

        validateWarehouse(location.getWarehouseId());

        existingLocation.setLocationCode(location.getLocationCode());
        existingLocation.setWarehouseId(location.getWarehouseId());
        existingLocation.setName(location.getName());
        existingLocation.setType(location.getType());
        existingLocation.setStatus(location.getStatus());
        existingLocation.setUpdatedAt(LocalDateTime.now());

        return locationRepository.save(existingLocation);
    }
}
