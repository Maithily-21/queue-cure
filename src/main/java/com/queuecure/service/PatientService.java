package com.queuecure.service;

import com.queuecure.dto.PatientRequest;
import com.queuecure.dto.QueueStatusResponse;
import com.queuecure.model.Patient;
import com.queuecure.model.PatientStatus;
import com.queuecure.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public Patient addPatient(PatientRequest request) {

        int token = (int) patientRepository.count() + 1;

        Patient patient = new Patient();

        patient.setPatientName(request.getPatientName());
        patient.setTokenNumber(token);
        patient.setStatus(PatientStatus.WAITING);

        return patientRepository.save(patient);
    }
    public Patient callNext() {

        Optional<Patient> currentPatient =
                patientRepository.findFirstByStatusOrderByTokenNumberAsc(
                        PatientStatus.IN_PROGRESS);

        if(currentPatient.isPresent()) {
            Patient current = currentPatient.get();
            current.setStatus(PatientStatus.COMPLETED);
            patientRepository.save(current);
        }

        Optional<Patient> nextPatient =
                patientRepository.findFirstByStatusOrderByTokenNumberAsc(
                        PatientStatus.WAITING);

        if(nextPatient.isPresent()) {
            Patient next = nextPatient.get();
            next.setStatus(PatientStatus.IN_PROGRESS);
            return patientRepository.save(next);
        }

        return null;
    }
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public QueueStatusResponse getQueueStatus() {

        Optional<Patient> current =
                patientRepository
                        .findFirstByStatusOrderByTokenNumberAsc(
                                PatientStatus.IN_PROGRESS);

        int waitingCount =
                patientRepository
                        .findByStatus(
                                PatientStatus.WAITING)
                        .size();

        Integer currentToken =
                current.map(Patient::getTokenNumber)
                        .orElse(0);

        QueueStatusResponse response =
                new QueueStatusResponse(
                        currentToken,
                        waitingCount
                );

        response.setAverageConsultationTime(5);

        return response;
    }


}