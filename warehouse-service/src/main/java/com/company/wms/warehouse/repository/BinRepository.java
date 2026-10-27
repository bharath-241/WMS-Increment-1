
package com.company.wms.warehouse.repository;

import com.company.wms.warehouse.bin.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinRepository extends JpaRepository<Bin, Long> {
}
