
package com.company.wms.warehouse.service;

import com.company.wms.warehouse.inventory.Inventory;
import com.company.wms.warehouse.repository.BinRepository;
import com.company.wms.warehouse.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BinRepository binRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            BinRepository binRepository) {
        this.inventoryRepository = inventoryRepository;
        this.binRepository = binRepository;
    }

    @Transactional
    public Inventory addStock(
            String itemCode,
            Long binId,
            Integer receivedQuantity) {

        if (itemCode == null || itemCode.isBlank()) {
            throw new IllegalArgumentException(
                    "Item code is required");
        }

        if (binId == null || !binRepository.existsById(binId)) {
            throw new IllegalArgumentException(
                    "Bin not found with id: " + binId);
        }

        if (receivedQuantity == null || receivedQuantity <= 0) {
            throw new IllegalArgumentException(
                    "Received quantity must be greater than zero");
        }

        Inventory inventory = inventoryRepository
                .findByItemCodeAndBinId(itemCode, binId)
                .orElseGet(() -> {
                    Inventory newInventory = new Inventory();
                    newInventory.setItemCode(itemCode);
                    newInventory.setBinId(binId);
                    newInventory.setQuantity(0);
                    return newInventory;
                });

        inventory.setQuantity(
                Math.addExact(
                        inventory.getQuantity(),
                        receivedQuantity));

        return inventoryRepository.save(inventory);
    }
}
