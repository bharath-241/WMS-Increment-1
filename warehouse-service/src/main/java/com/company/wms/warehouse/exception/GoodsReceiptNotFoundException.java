package com.company.wms.warehouse.exception;

public class GoodsReceiptNotFoundException extends RuntimeException {

    public GoodsReceiptNotFoundException(String message) {
        super(message);
    }
}