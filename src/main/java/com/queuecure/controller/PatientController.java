package com.queuecure.controller;

import com.queuecure.dto.PatientRequest;
import com.queuecure.dto.QueueStatusResponse;
import com.queuecure.model.Patient;
import com.queuecure.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5176")
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping
    public Patient addPatient(@RequestBody PatientRequest request) {
        return patientService.addPatient(request);
    }
    @PostMapping("/call-next")
    public Patient callNext() {
        return patientService.callNext();
    }
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    @GetMapping("/queue-status")
    public QueueStatusResponse getQueueStatus() {
        return patientService.getQueueStatus();
    }
}
