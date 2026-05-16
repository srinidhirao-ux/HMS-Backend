package com.hms.service;

import com.hms.entity.Bill;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BillService {

    private static final BigDecimal GST_RATE = new BigDecimal("0.18");

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAllByOrderByCreatedAtDesc();
    }

    public Bill createBill(Bill bill) {
        BigDecimal subTotal = bill.getConsultationFee()
                .add(bill.getMedicineFee())
                .add(bill.getTestFee());

        // GST is calculated at 18% for this beginner project.
        BigDecimal gst = subTotal.multiply(GST_RATE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalAmount = subTotal.add(gst).setScale(2, RoundingMode.HALF_UP);

        bill.setGst(gst);
        bill.setTotalAmount(totalAmount);

        return billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));

        billRepository.delete(bill);
    }
}
