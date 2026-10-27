
package com.company.wms.warehouse.exception;

public class PutAwayNotFoundException extends RuntimeException {

    public PutAwayNotFoundException(Long id) {
        super("Put-Away record not found with id: " + id);
    }
}
