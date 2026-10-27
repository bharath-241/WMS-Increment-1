
package com.company.wms.warehouse.consumer;

import com.company.wms.warehouse.event.GoodsReceiptCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GoodsReceiptEventConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(GoodsReceiptEventConsumer.class);

    public GoodsReceiptEventConsumer() {
        log.info("GoodsReceiptEventConsumer bean created");
    }

    @KafkaListener(
            topics = "goods-receipt-created",
            groupId = "wms-inventory-group"
    )
    public void consume(GoodsReceiptCreatedEvent event) {

        log.info(
                "Received Goods Receipt event: receiptId={}, receiptNumber={}, occurredAt={}",
                event.goodsReceiptId(),
                event.receiptNumber(),
                event.occurredAt()
        );
    }
}
