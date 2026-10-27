
package com.company.wms.warehouse.repository;

import com.company.wms.warehouse.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByItemCodeAndBinId(
            String itemCode,
            Long binId
    );
}
