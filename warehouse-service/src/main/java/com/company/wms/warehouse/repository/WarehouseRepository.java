package com.company.wms.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.wms.warehouse.domain.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}