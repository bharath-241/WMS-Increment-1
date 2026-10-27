
package com.company.wms.warehouse.controller;

import com.company.wms.warehouse.bin.Bin;
import com.company.wms.warehouse.service.BinService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    // Create a new bin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bin createBin(@Valid @RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    // Get a bin by ID
    @GetMapping("/{id}")
    public Bin getBinById(@PathVariable Long id) {
        return binService.getBinById(id);
    }

    // Get all bins
    @GetMapping
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    // Update an existing bin
    @PutMapping("/{id}")
    public Bin updateBin(
            @PathVariable Long id,
            @Valid @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    // Delete a bin by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
    }
}
