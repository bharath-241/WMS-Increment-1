
package com.company.wms.warehouse.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GoodsReceiptEventPublisher {

    private static final String TOPIC = "goods-receipt-created";

    private final KafkaTemplate<String, GoodsReceiptCreatedEvent> kafkaTemplate;

    public GoodsReceiptEventPublisher(
            KafkaTemplate<String, GoodsReceiptCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(GoodsReceiptCreatedEvent event) {
        kafkaTemplate.send(
                TOPIC,
                String.valueOf(event.goodsReceiptId()),
                event
        );
    }
}
