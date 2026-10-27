
package com.company.wms.warehouse.repository;

import com.company.wms.warehouse.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
