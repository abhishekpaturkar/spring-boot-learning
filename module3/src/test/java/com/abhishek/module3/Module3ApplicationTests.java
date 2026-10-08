package com.abhishek.module3;

import com.abhishek.module3.entities.ProductEntity;
import com.abhishek.module3.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class Module3ApplicationTests {

	@Autowired
    private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository() {
//		List<ProductEntity> productEntities = productRepository.findAll();
		List<ProductEntity> productEntities = productRepository.findByTitle("Pepsi");
		System.out.println(productEntities);
	}

	@Test
	void testCreatedAfter() {
		List<ProductEntity> productEntities = productRepository.findByCreatedAtAfter(
				LocalDateTime.of(2026, 10, 7, 0, 0, 0)
		);
		System.out.println(productEntities);
	}

	@Test
	void testFindByQuantityGreaterThanAndPriceLessThan() {
		List<ProductEntity> productEntities = productRepository.findByQuantityGreaterThanAndPriceLessThan(
				7, BigDecimal.valueOf(35.0));
		System.out.println(productEntities);
	}

	@Test
	void testFindByQuantityGreaterThanOrPriceLessThan() {
		List<ProductEntity> productEntities = productRepository.findByQuantityGreaterThanOrPriceLessThan(
				10, BigDecimal.valueOf(20.0));
		System.out.println(productEntities);
	}

	@Test
	void testFindByTitleLike() {
		List<ProductEntity> productEntities = productRepository.findByTitleLike("%Oreo%");
		System.out.println(productEntities);
	}

	@Test
	void testFindByTitleContaining() {
		List<ProductEntity> productEntities = productRepository.findByTitleContaining("Oreo");
		System.out.println(productEntities);
	}

	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> productEntity = productRepository.findByTitleAndPrice(
				"Milo", BigDecimal.valueOf(2.3)
		);

		// Method inference
		productEntity.ifPresent(System.out::println);
	}

}
