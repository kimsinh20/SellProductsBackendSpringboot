package com.example.employee.service.Impl;

import com.example.employee.model.Product;
import com.example.employee.respository.ProductRepository;
import com.example.employee.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllPro(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findProTrending(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public  Boolean createProduct(Product product) {
         productRepository.save(product);
         return true;
    }

    @Override
    public Boolean updateProductById(Product pro) {
        productRepository.save(pro);
        return true;
    }

    @Override
    public Boolean deleteProductId(Long id) {
        Boolean isDelete = true;
        if(productRepository.findById(id).isPresent()) {
            return false;
        } else {
            productRepository.deleteById(id);
        }
        return isDelete;
    }

}
