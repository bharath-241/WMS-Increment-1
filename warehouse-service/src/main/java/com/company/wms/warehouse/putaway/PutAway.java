
package com.company.wms.warehouse.putaway;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "put_away")
public class PutAway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Goods Receipt ID is required")
    @Positive(message = "Goods Receipt ID must be positive")
    @Column(nullable = false)
    private Long goodsReceiptId;

    @NotNull(message = "Bin ID is required")
    @Positive(message = "Bin ID must be positive")
    @Column(nullable = false)
    private Long binId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private Integer quantity;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "PENDING|IN_PROGRESS|COMPLETED",
            message = "Status must be PENDING, IN_PROGRESS, or COMPLETED"
    )
    @Column(nullable = false, length = 20)
    private String status = "PENDING";

    private LocalDateTime createdAt;

    public PutAway() {
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Long getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(Long goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
    }

    public Long getBinId() {
        return binId;
    }

    public void setBinId(Long binId) {
        this.binId = binId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
