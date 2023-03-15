package com.example.employee.controller;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Product;
import com.example.employee.Dto.Respond.RespProducts;
import com.example.employee.respository.ProductRepository;
import com.example.employee.service.Impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;


    // get all employees

    //        @GetMapping()
//        public List<Product> GetAllProduct() {
//            return productService.findAll();
//        }
    @GetMapping()
    public ResponseEntity<?> getAllProPage(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAllPro(pageable);
        RespProducts respond = new RespProducts(productPage.getContent(), productPage.getSize(), productPage.getTotalElements(), productPage.getTotalPages());
        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @GetMapping("/trending")
    public ResponseEntity<?> getAllProductTrending(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "4") int size) {
        Pageable pageable = PageRequest.of(page, size,Sort.by("price").descending());
        Page<Product> productPage = productService.findProTrending(pageable);
        RespProducts respond = new RespProducts(productPage.getContent(), productPage.getSize(), productPage.getTotalElements(), productPage.getTotalPages());
        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> GetProductById(@PathVariable long id) {
        Optional<Product> product = productService.findProductById(id);
        return product.isPresent() ? ResponseEntity.ok().body(product.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<?> AddProductList(@RequestBody Product pro) {
        return new ResponseEntity<>(productRepository.save(pro), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateProductById(@PathVariable(value = "id") long id, @RequestBody Product pro) throws ResourceNotFoundException {

        Optional<Product> product = productService.findProductById(id);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pro.setId(id);
        String messeage = productService.updateProductById(pro) ? "update sucsces" : "not sussces";
        return ResponseEntity.ok().body(messeage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable(value = "id") long id) {
        if (!productService.deleteProductId(id)) {
            ResponseEntity.badRequest().body("delete fall");
        }
        return ResponseEntity.ok().body("delete sucssces");
    }
}
