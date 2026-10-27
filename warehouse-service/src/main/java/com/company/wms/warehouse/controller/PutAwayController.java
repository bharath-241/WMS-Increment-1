
package com.company.wms.warehouse.controller;

import com.company.wms.warehouse.putaway.PutAway;
import com.company.wms.warehouse.service.PutAwayService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/put-aways")
public class PutAwayController {

    private final PutAwayService putAwayService;

    public PutAwayController(PutAwayService putAwayService) {
        this.putAwayService = putAwayService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PutAway createPutAway(
            @Valid @RequestBody PutAway putAway) {
        return putAwayService.createPutAway(putAway);
    }

    @GetMapping("/{id}")
    public PutAway getPutAwayById(@PathVariable Long id) {
        return putAwayService.getPutAwayById(id);
    }

    @GetMapping
    public List<PutAway> getAllPutAways() {
        return putAwayService.getAllPutAways();
    }

    @PutMapping("/{id}")
    public PutAway updatePutAway(
            @PathVariable Long id,
            @Valid @RequestBody PutAway putAway) {
        return putAwayService.updatePutAway(id, putAway);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePutAway(@PathVariable Long id) {
        putAwayService.deletePutAway(id);
    }
}
