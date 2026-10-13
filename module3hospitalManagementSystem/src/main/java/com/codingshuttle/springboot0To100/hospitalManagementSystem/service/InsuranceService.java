package com.codingshuttle.springboot0To100.hospitalManagementSystem.service;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.Insurance;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.Patient;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.repository.InsuranceRepository;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    // Transactional is important here
    // if not then persistence context will be different
    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId) {
        // Select Query
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        // As patient is owner of the relation
        // Insurance create query
        // then Patient update query
        patient.setInsurance(insurance);

        insurance.setPatient(patient); // Optional (Just to maintain bidirectional consistency)

        return insurance;
    }

    @Transactional
    public Insurance updateInsuranceOfPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return insurance;
    }

    @Transactional
    public Patient removeInsuranceOfPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(null);

        return patient;
    }

}
