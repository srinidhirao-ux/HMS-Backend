package com.hms.service;

import com.hms.dto.DashboardStatsResponse;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.BillRepository;
import com.hms.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillRepository billRepository;

    public DashboardService(PatientRepository patientRepository,
                            AppointmentRepository appointmentRepository,
                            BillRepository billRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.billRepository = billRepository;
    }

    public DashboardStatsResponse getStats() {
        return new DashboardStatsResponse(
                patientRepository.count(),
                appointmentRepository.count(),
                billRepository.getTotalRevenue(),
                patientRepository.findTop5ByOrderByCreatedAtDesc()
        );
    }
}
