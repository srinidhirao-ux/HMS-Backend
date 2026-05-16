package com.hms.dto;

import com.hms.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DashboardStatsResponse {

    private long totalPatients;
    private long totalAppointments;
    private BigDecimal totalRevenue;
    private List<Patient> recentPatients;
}
