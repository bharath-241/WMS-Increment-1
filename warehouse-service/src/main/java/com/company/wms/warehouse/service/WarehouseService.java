package com.company.wms.warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.wms.warehouse.domain.Warehouse;
import com.company.wms.warehouse.exception.WarehouseNotFoundException;
import com.company.wms.warehouse.repository.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse createWarehouse(Warehouse warehouse) {

        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        warehouse.setCreatedAt(now);
        warehouse.setUpdatedAt(now);

        return warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(
                        "Warehouse not found with id: " + id));
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {

        Warehouse existingWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(
                        "Warehouse not found with id: " + id));

        existingWarehouse.setWarehouseCode(warehouse.getWarehouseCode());
        existingWarehouse.setName(warehouse.getName());
        existingWarehouse.setAddress(warehouse.getAddress());
        existingWarehouse.setStatus(warehouse.getStatus());
        existingWarehouse.setUpdatedAt(java.time.LocalDateTime.now());

        return warehouseRepository.save(existingWarehouse);
    }

    public void deleteWarehouse(Long id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(
                        "Warehouse not found with id: " + id));

        warehouseRepository.delete(warehouse);
    }
}