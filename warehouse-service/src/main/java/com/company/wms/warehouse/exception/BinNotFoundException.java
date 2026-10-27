
package com.company.wms.warehouse.exception;

public class BinNotFoundException extends RuntimeException {

    public BinNotFoundException(String message) {
        super(message);
    }
}
