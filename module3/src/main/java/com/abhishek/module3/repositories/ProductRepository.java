package com.abhishek.module3.repositories;

import com.abhishek.module3.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // for making custom query creation
    // REFER SPRING DOCUMENTATION:
    // https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html

    // We need to define method name with certain style
    // Like here findByTitle
    // findByTitles will not work because we don't have a field named "titles" in the entity
    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(Integer quantity, BigDecimal price);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(Integer quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String pattern);

    List<ProductEntity> findByTitleContaining(String pattern);

    // Query on UniqueConstraint
//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    // To Define complex queries using SQL or JPQL
    // Here given title not title_x because this a JPQL level which will understand title
    // because it is a field inside Entity, hibernate will convert this title to title_x
    // title_x is db table column name as defined in the entity

    // NOTE: Here all the table, column name should be like Java level not db level
    // ProductEntity should be there not product_table

    @Query("select e from ProductEntity e where e.title = ?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    // OR
//    @Query("select e from ProductEntity e where e.title = :title and e.price=:price")
//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
}
