
package com.company.wms.warehouse.service;

import com.company.wms.warehouse.bin.Bin;
import com.company.wms.warehouse.exception.BinNotFoundException;
import com.company.wms.warehouse.repository.BinRepository;
import com.company.wms.warehouse.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BinService {

    private final BinRepository binRepository;
    private final LocationRepository locationRepository;

    public BinService(
            BinRepository binRepository,
            LocationRepository locationRepository) {
        this.binRepository = binRepository;
        this.locationRepository = locationRepository;
    }

    // Validate that the Location exists
    private void validateLocation(Long locationId) {
        if (locationId == null
                || !locationRepository.existsById(locationId)) {
            throw new IllegalArgumentException(
                    "Location not found with id: " + locationId);
        }
    }

    // Create a new bin
    public Bin createBin(Bin bin) {
        validateLocation(bin.getLocationId());

        LocalDateTime now = LocalDateTime.now();
        bin.setCreatedAt(now);
        bin.setUpdatedAt(now);

        return binRepository.save(bin);
    }

    // Get a bin by ID
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new BinNotFoundException(
                        "Bin not found with id: " + id));
    }

    // Get all bins
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    // Update an existing bin
    public Bin updateBin(Long id, Bin bin) {
        Bin existingBin = binRepository.findById(id)
                .orElseThrow(() -> new BinNotFoundException(
                        "Bin not found with id: " + id));

        validateLocation(bin.getLocationId());

        existingBin.setBinCode(bin.getBinCode());
        existingBin.setLocationId(bin.getLocationId());
        existingBin.setName(bin.getName());
        existingBin.setStatus(bin.getStatus());
        existingBin.setUpdatedAt(LocalDateTime.now());

        return binRepository.save(existingBin);
    }

    // Delete a bin by ID
    public void deleteBin(Long id) {
        Bin existingBin = binRepository.findById(id)
                .orElseThrow(() -> new BinNotFoundException(
                        "Bin not found with id: " + id));

        binRepository.delete(existingBin);
    }
}
