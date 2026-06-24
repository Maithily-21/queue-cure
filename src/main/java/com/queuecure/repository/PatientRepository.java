package com.queuecure.repository;

import com.queuecure.model.Patient;
import com.queuecure.model.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findFirstByStatusOrderByTokenNumberAsc(
            PatientStatus status);

    List<Patient> findByStatus(PatientStatus status);
}
