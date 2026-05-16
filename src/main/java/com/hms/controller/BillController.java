package com.hms.controller;

import com.hms.dto.ApiResponse;
import com.hms.entity.Bill;
import com.hms.service.BillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ApiResponse<List<Bill>> getAllBills() {
        return ApiResponse.success("Bills fetched successfully", billService.getAllBills());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Bill>> createBill(@Valid @RequestBody Bill bill) {
        Bill savedBill = billService.createBill(bill);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Bill created successfully", savedBill));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ApiResponse.success("Bill deleted successfully", null);
    }
}
