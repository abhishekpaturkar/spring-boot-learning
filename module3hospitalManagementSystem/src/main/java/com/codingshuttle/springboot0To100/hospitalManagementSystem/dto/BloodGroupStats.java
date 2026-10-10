package com.codingshuttle.springboot0To100.hospitalManagementSystem.dto;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.type.BloodGroupType;
import lombok.Data;

// Here we are retrieving the data which is not by default in the DB,
// Like count of blood Group
@Data
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;
    private final Long count;
}
