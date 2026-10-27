package com.company.wms.warehouse.repository;

import com.company.wms.warehouse.inbound.GoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsReceiptRepository extends JpaRepository<GoodsReceipt, Long> {
}