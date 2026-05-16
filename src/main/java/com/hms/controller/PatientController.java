package com.hms.controller;

import com.hms.dto.ApiResponse;
import com.hms.entity.Patient;
import com.hms.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ApiResponse<List<Patient>> getAllPatients(@RequestParam(required = false) String search) {
        return ApiResponse.success("Patients fetched successfully", patientService.getAllPatients(search));
    }

    @GetMapping("/{id}")
    public ApiResponse<Patient> getPatientById(@PathVariable Long id) {
        return ApiResponse.success("Patient fetched successfully", patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Patient created successfully", savedPatient));
    }

    @PutMapping("/{id}")
    public ApiResponse<Patient> updatePatient(@PathVariable Long id, @Valid @RequestBody Patient patient) {
        return ApiResponse.success("Patient updated successfully", patientService.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ApiResponse.success("Patient deleted successfully", null);
    }
}
