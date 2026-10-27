
package com.company.wms.warehouse.service;

import com.company.wms.warehouse.exception.PutAwayNotFoundException;
import com.company.wms.warehouse.putaway.PutAway;
import com.company.wms.warehouse.repository.BinRepository;
import com.company.wms.warehouse.repository.GoodsReceiptRepository;
import com.company.wms.warehouse.repository.PutAwayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PutAwayService {

    private final PutAwayRepository putAwayRepository;
    private final GoodsReceiptRepository goodsReceiptRepository;
    private final BinRepository binRepository;

    public PutAwayService(
            PutAwayRepository putAwayRepository,
            GoodsReceiptRepository goodsReceiptRepository,
            BinRepository binRepository) {

        this.putAwayRepository = putAwayRepository;
        this.goodsReceiptRepository = goodsReceiptRepository;
        this.binRepository = binRepository;
    }

    public PutAway createPutAway(PutAway putAway) {
        validateGoodsReceipt(putAway.getGoodsReceiptId());
        validateBin(putAway.getBinId());

        // New records must start in PENDING status.
        putAway.setStatus("PENDING");

        return putAwayRepository.save(putAway);
    }

    @Transactional(readOnly = true)
    public PutAway getPutAwayById(Long id) {
        return putAwayRepository.findById(id)
                .orElseThrow(() -> new PutAwayNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<PutAway> getAllPutAways() {
        return putAwayRepository.findAll();
    }

    public PutAway updatePutAway(Long id, PutAway updatedPutAway) {
        PutAway existingPutAway = getPutAwayById(id);

        validateGoodsReceipt(updatedPutAway.getGoodsReceiptId());
        validateBin(updatedPutAway.getBinId());

        String currentStatus = existingPutAway.getStatus();
        String requestedStatus = updatedPutAway.getStatus();

        validateStatusTransition(currentStatus, requestedStatus);

        existingPutAway.setGoodsReceiptId(
                updatedPutAway.getGoodsReceiptId());

        existingPutAway.setBinId(
                updatedPutAway.getBinId());

        existingPutAway.setQuantity(
                updatedPutAway.getQuantity());

        existingPutAway.setStatus(requestedStatus);

        return putAwayRepository.save(existingPutAway);
    }

    public void deletePutAway(Long id) {
        PutAway existingPutAway = getPutAwayById(id);
        putAwayRepository.delete(existingPutAway);
    }

    private void validateStatusTransition(
            String currentStatus,
            String requestedStatus) {

        boolean validTransition =
                ("PENDING".equals(currentStatus)
                        && "IN_PROGRESS".equals(requestedStatus))
                        || ("IN_PROGRESS".equals(currentStatus)
                        && "COMPLETED".equals(requestedStatus));

        if (!validTransition) {
            throw new IllegalArgumentException(
                    "Invalid Put-Away status transition from "
                            + currentStatus + " to " + requestedStatus
                            + ". Allowed transitions: PENDING to IN_PROGRESS, "
                            + "IN_PROGRESS to COMPLETED.");
        }
    }

    private void validateGoodsReceipt(Long goodsReceiptId) {
        if (goodsReceiptId == null
                || !goodsReceiptRepository.existsById(goodsReceiptId)) {
            throw new IllegalArgumentException(
                    "Goods Receipt not found with id: " + goodsReceiptId);
        }
    }

    private void validateBin(Long binId) {
        if (binId == null || !binRepository.existsById(binId)) {
            throw new IllegalArgumentException(
                    "Bin not found with id: " + binId);
        }
    }
}
