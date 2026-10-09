package com.abhishek.module3.repositories;

import com.abhishek.module3.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Explicitly mentioned Price -> OrderByPrice so sorting will take place on Price
    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(Integer quantity, BigDecimal price);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(Integer quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String pattern);

    List<ProductEntity> findByTitleContainingIgnoreCase(String pattern, Pageable pageable);

    @Query("select e from ProductEntity e where e.title = ?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

}
