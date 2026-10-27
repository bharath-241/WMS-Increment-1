
package com.company.wms.warehouse.event;

import java.time.LocalDateTime;

public record GoodsReceiptCreatedEvent(
        Long goodsReceiptId,
        String receiptNumber,
        LocalDateTime occurredAt
) {
}
