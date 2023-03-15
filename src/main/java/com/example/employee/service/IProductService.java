package com.example.employee.service;

import com.example.employee.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Page<Product> findAllPro(Pageable pageable);
    Page<Product> findProTrending(Pageable pageable);
    Optional<Product> findProductById(Long id);
    Boolean createProduct(Product product);
    Boolean updateProductById(Product product);
    Boolean deleteProductId(Long id);
}
