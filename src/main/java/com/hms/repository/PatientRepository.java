package com.hms.repository;

import com.hms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findTop5ByOrderByCreatedAtDesc();

    @Query("""
            SELECT p FROM Patient p
            WHERE LOWER(p.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(p.phone) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(p.disease) LIKE LOWER(CONCAT('%', :keyword, '%'))
            ORDER BY p.createdAt DESC
            """)
    List<Patient> searchPatients(@Param("keyword") String keyword);
}
