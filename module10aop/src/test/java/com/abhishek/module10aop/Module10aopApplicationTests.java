package com.abhishek.module10aop;

import com.abhishek.module10aop.service.impl.ShipmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Module10aopApplicationTests {

	@Autowired
	private ShipmentServiceImpl shipmentService;

	@Test
	void aopTestOrderPackage() {
		shipmentService.orderPackage(4L);
	}

	@Test
	void aopTestTrackPackage() {
		shipmentService.trackPackage(4L);
	}
}
