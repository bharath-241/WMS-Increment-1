
package com.company.wms.warehouse.repository;

import com.company.wms.warehouse.putaway.PutAway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PutAwayRepository extends JpaRepository<PutAway, Long> {
}
