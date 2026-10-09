package com.abhishek.module3.controllers;

import com.abhishek.module3.entities.ProductEntity;
import com.abhishek.module3.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    // For sake of tutorial we will directly use Repository here
    // Also will not create a DTO class so using Entity directly

    private final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Page<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "0") Integer pageNumber) {
//        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
//        return productRepository.findAll(sort);
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy).ascending());

        return productRepository.findAll(pageable);
    }

    @GetMapping("/sorted")
    public List<ProductEntity> getProductsSortedByPrice() {
        Sort sort = Sort.by(
            Sort.Order.desc("price"));
        return productRepository.findAll(sort);
    }

    @GetMapping("/title")
    public List<ProductEntity> getProductsSortedByTitle(@RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestParam(defaultValue = "0") Integer pageNumber,
                                                        @RequestParam(defaultValue = "") String title) {
        return productRepository.findByTitleContainingIgnoreCase(
                title,
                PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy))
        );
    }


}
