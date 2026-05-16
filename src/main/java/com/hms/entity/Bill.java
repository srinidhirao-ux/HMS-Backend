package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Consultation fee is required")
    @DecimalMin(value = "0.0", message = "Consultation fee cannot be negative")
    private BigDecimal consultationFee;

    @NotNull(message = "Medicine fee is required")
    @DecimalMin(value = "0.0", message = "Medicine fee cannot be negative")
    private BigDecimal medicineFee;

    @NotNull(message = "Test fee is required")
    @DecimalMin(value = "0.0", message = "Test fee cannot be negative")
    private BigDecimal testFee;

    private BigDecimal gst;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAtBeforeSave() {
        this.createdAt = LocalDateTime.now();
    }
}
