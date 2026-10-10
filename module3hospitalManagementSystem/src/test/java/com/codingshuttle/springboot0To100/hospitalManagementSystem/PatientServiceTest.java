package com.codingshuttle.springboot0To100.hospitalManagementSystem;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.dto.BloodGroupStats;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.dto.CPatientInfo;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.dto.IPatientInfo;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.Patient;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient() {
//        List<Patient> patients = patientRepository.findAll();
//        List<IPatientInfo> patients = patientRepository.getAllPatientInfo();
//        List<CPatientInfo> patients = patientRepository.getAllPatientInfoConcrete();

//        List<BloodGroupStats> patients = patientRepository.getBloodGroupStats();
//        for(BloodGroupStats patient : patients){
//            System.out.println(patient);
//        }

        int rowsAffected = patientRepository.updatePatientNameWithId("Abhishek Paturkar", 1L);
        System.out.println("Rows affected: " + rowsAffected);
    }


}
