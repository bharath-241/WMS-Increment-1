
package com.company.wms.warehouse.service;

import com.company.wms.warehouse.event.GoodsReceiptCreatedEvent;
import com.company.wms.warehouse.event.GoodsReceiptEventPublisher;
import com.company.wms.warehouse.exception.GoodsReceiptNotFoundException;
import com.company.wms.warehouse.inbound.GoodsReceipt;
import com.company.wms.warehouse.repository.GoodsReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsReceiptService {

    private final GoodsReceiptRepository goodsReceiptRepository;
    private final GoodsReceiptEventPublisher eventPublisher;

    public GoodsReceiptService(
            GoodsReceiptRepository goodsReceiptRepository,
            GoodsReceiptEventPublisher eventPublisher) {
        this.goodsReceiptRepository = goodsReceiptRepository;
        this.eventPublisher = eventPublisher;
    }

    // Create a Goods Receipt and publish an event
    public GoodsReceipt createGoodsReceipt(GoodsReceipt goodsReceipt) {
        GoodsReceipt savedReceipt = goodsReceiptRepository.save(goodsReceipt);

        GoodsReceiptCreatedEvent event = new GoodsReceiptCreatedEvent(
                savedReceipt.getId(),
                savedReceipt.getReceiptNumber(),
                LocalDateTime.now()
        );

        eventPublisher.publish(event);

        return savedReceipt;
    }

    // Get a Goods Receipt by ID
    public GoodsReceipt getGoodsReceiptById(Long id) {
        return goodsReceiptRepository.findById(id)
                .orElseThrow(() -> new GoodsReceiptNotFoundException(
                        "Goods Receipt not found with id: " + id));
    }

    // Get all Goods Receipts
    public List<GoodsReceipt> getAllGoodsReceipts() {
        return goodsReceiptRepository.findAll();
    }
}
