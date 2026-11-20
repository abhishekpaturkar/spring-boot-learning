package com.abhishek.module10aop.service;

public interface ShipmentService {
    String orderPackage(Long orderId);

    String trackPackage(Long orderId);
}
