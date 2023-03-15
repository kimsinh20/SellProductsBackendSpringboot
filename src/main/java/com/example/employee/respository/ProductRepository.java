package com.example.employee.respository;

import com.example.employee.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("SELECT e FROM products e ORDER BY e.price ASC")
//    List<Product> findAllProduct();
}