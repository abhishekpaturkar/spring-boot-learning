package com.codingshuttle.springboot0To100.hospitalManagementSystem.service;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.Patient;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // by adding @Transactional all the below execution will be in same context
    // i.e PersistenceContext
    @Transactional
    public void testPatientTransaction() {
        // Before adding transactional 2 db calls was happening, now only 1
        Patient p1 = patientRepository.findById(1L).orElseThrow();
        Patient p2 = patientRepository.findById(1L).orElseThrow();

        System.out.println(p1 + "  " + p2);

        // Before adding transactional this will return false, now true
        // because now p1 and p2 is in same context window
        System.out.println(p1 == p2);

        // If we change anything in the objects then it will reflect in the db when the transaction ends
        // example
        // This will result in the update query

        // Persistence context check all the entities before transaction ends and put them in the db
        // Dirtied entities
        p1.setName("Random Name");
    }

    @Transactional
    public void deletePatient(Long patientId) {
        patientRepository.findById(patientId).orElseThrow();
        patientRepository.deleteById(patientId);
    }
}
