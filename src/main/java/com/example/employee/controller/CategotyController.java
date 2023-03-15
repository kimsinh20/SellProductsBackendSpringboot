package com.example.employee.controller;

import com.example.employee.model.Category;
import com.example.employee.respository.CategoryRepository;
import com.example.employee.service.Impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/category")
public class CategotyController {
    @Autowired
   private CategoryService categoryService;
    @GetMapping()
    public List<Category>  getAllCategory() {
        return categoryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long id) {
        Category c = categoryService.findCategoryById(id).get();
        return ResponseEntity.ok().body(c);
    }
    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateCategory(@PathVariable(value = "id") Long id , @RequestBody Category category) {
        Optional<Category> category1 =  categoryService.findCategoryById(id);
        if(!category1.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        category.setCategoryId(id);
        return categoryService.updateCategoryById(category) ? ResponseEntity.ok().body((category)): new ResponseEntity<>("not update sucsseces",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteCategory(@PathVariable(value = "id") Long id) {
        return categoryService.deleteCategoryId(id) ?  ResponseEntity.ok().body("xoa thanh cong") : ResponseEntity.ok().body("not deleted sucssces");
    }

}
