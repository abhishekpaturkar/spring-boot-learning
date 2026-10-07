package com.abhishek.module3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
// if we want table name to be different from class name then use "name" in @Table
@Table(
        name = "product_table",
        uniqueConstraints = {
                // SKU should be unique
                @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                // Title_X and Price should be unique pair
                // Biscuit -> Rs.20 should not come again in the DB
                @UniqueConstraint(name = "title_price_unique", columnNames = {"title_x", "price"})
        },
        indexes = {
                // Creating Indexes on sku to query
                @Index(name = "sku_index", columnList = "sku")
        }
)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mark this column of sku as not nullable
    // Also we can add the length limit
    @Column(nullable = false, length = 20)
    private String sku;

    // If we don't want the name of the column to be exactly like field name
    // here by default the column name will be title
    // to change the column name from title to title_name we have to use @Column annotation
    @Column(name = "title_x")
    private String title;

    private BigDecimal price;

    private Integer quantity;

    // DB will take care of creation time & update time
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
