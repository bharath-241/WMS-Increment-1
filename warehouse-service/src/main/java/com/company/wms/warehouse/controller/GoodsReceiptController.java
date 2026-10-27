package com.company.wms.warehouse.controller;

import com.company.wms.warehouse.inbound.GoodsReceipt;
import com.company.wms.warehouse.service.GoodsReceiptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goods-receipts")
public class GoodsReceiptController {

    private final GoodsReceiptService goodsReceiptService;

    public GoodsReceiptController(GoodsReceiptService goodsReceiptService) {
        this.goodsReceiptService = goodsReceiptService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoodsReceipt createGoodsReceipt(
            @Valid @RequestBody GoodsReceipt goodsReceipt) {

        return goodsReceiptService.createGoodsReceipt(goodsReceipt);
    }

    @GetMapping("/{id}")
    public GoodsReceipt getGoodsReceiptById(@PathVariable Long id) {
        return goodsReceiptService.getGoodsReceiptById(id);
    }

    @GetMapping
    public List<GoodsReceipt> getAllGoodsReceipts() {
        return goodsReceiptService.getAllGoodsReceipts();
    }
}