package com.abhishek.module4.clients.impl;

import com.abhishek.module4.advice.ApiResponse;
import com.abhishek.module4.clients.EmployeeClient;
import com.abhishek.module4.dto.EmployeeDTO;
import com.abhishek.module4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList =  restClient.get()
                    .uri("employees")
                    .retrieve()
//                    .body(EmployeeDTO.class) If only return the EmployeeDTO
                    .body(new ParameterizedTypeReference<>() {}); // Return type is List so need to use this here
            return  employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employee/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return  employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try {
            // for using toEntity
            // ResponseEntity have headers, body, status, etc
            // ResponseEntity<ApiResponse<EmployeeDTO>>
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        // Should use Logger here
                        System.out.println("Error occurred: " + new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    // instead of body we can use ResponseEntity as well
//                    .toEntity(new ParameterizedTypeReference<>() {})
                    .body(new ParameterizedTypeReference<>() {});
         

            return  employeeDTOApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
