package com.abhishek.module10aop;

import com.abhishek.module10aop.service.impl.ShipmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class Module10aopApplicationTests {

	@Autowired
	private ShipmentServiceImpl shipmentService;

	@Test
	void aopTestOrderPackage() {
		String orderString = shipmentService.orderPackage(4L);
		log.info(orderString);
	}

	@Test
	void aopTestTrackPackage() {
		shipmentService.trackPackage(4L);
	}
}
