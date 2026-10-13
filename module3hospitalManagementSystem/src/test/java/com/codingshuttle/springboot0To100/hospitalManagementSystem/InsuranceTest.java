package com.codingshuttle.springboot0To100.hospitalManagementSystem;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.Insurance;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.repository.InsuranceRepository;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.service.InsuranceService;
import com.codingshuttle.springboot0To100.hospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PatientService patientService;

    @Test
    public void testAssignInsuranceToPatient() {
        Insurance insurance = Insurance.builder()
                .provider("HDFC Ergo")
                .policyNumber("HDFC_2352")
                .validUntil(LocalDate.of(2030, 1, 1))
                .build();

        // Currently this will throw error (org.hibernate.TransientObjectException)
        // because patient in Persistence state but the insurance is in Transient state
        // So Patient is not able to access Insurance inside "assignInsuranceToPatient" method

        // Above issue is solved by cascading
        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, 1L);

        System.out.println(updatedInsurance);
    }

    @Test
    public void testDeleteInsurance() {
        patientService.deletePatient(1L);
    }

    @Test
    public void testUpdateInsurance() {
        var patient = insuranceService.removeInsuranceOfPatient(1L);
        System.out.println(patient);
    }
}
