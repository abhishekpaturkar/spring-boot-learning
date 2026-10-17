package com.abhishek.module4.clients.impl;

import com.abhishek.module4.advice.ApiResponse;
import com.abhishek.module4.clients.EmployeeClient;
import com.abhishek.module4.dto.EmployeeDTO;
import com.abhishek.module4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        // Types of log
//        log.error("error log");
//        log.warn("warn log");
//        log.info("info log");
        // Debug and Trace logs are by default not enabled
//        log.debug("debug log");
//        log.trace("trace log");

        log.trace("Trying to retrieve all employees in getAllEmployees");
        try {
            log.info("Attempting to retrieve all employees in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList =  restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {});

            log.debug("Successfully retrieved employee in getAllEmployees");
            log.trace("Retrieved employee in getAllEmployees : {}", employeeDTOList.getData());
            return  employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employee/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not retrieve the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {});

            return  employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create an employees in createEmployee {}", employeeDTO);
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClient error occurred in createEmployee {}", res);
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {});
         
            log.trace("Successfully created employee in createEmployee : {}", employeeDTOApiResponse.getBody());
            return  employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            log.error("Exception occurred in createEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
