package com.codingshuttle.springboot0To100.hospitalManagementSystem.dto;

import lombok.Data;

// Here we can modify or perform some operations on the data after retrieval as this a class
// We have setters and getters here
@Data
public class CPatientInfo {

    // As we added final here it will automatically create a NoArgsConstructor, AllArgsConstructor, Getter and Setter
    private final Long id;
    private final String name;
}
