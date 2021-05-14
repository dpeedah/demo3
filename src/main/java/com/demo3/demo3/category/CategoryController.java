package com.demo3.demo3.category;

import com.demo3.demo3.actor.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping(path="/all")
    public ResponseEntity<List<Category>> getCategories() {
        Iterable<Category> a = categoryRepo.findAll();
        List categories = (List) a;
        return ResponseEntity.ok(categories);
    }

    @GetMapping(path="/byid/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long category_id){
        Category category = categoryRepo.findById(category_id).get();
        return new ResponseEntity<Category>(category,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long category_id){
        boolean exists = categoryRepo.existsById(category_id);
        if (!exists){
            throw new IllegalStateException("Actor with ID of " + category_id + "does not exist");
        }
        categoryRepo.deleteById(category_id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        Optional<Category> categoryByName = categoryRepo.findCategoryByName(category.getName());
        if (categoryByName.isPresent()){
            throw new IllegalStateException("Category already exists");
        }
        categoryRepo.save(category);
        return new ResponseEntity<Category>(category,HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<Category> modifyCategoryById(@PathVariable("id") Long id, @RequestParam (required = false) String name){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new IllegalStateException("Category with Id: " + id + " does not exist"));

        if  (name != null && name.length() > 0){
            category.setName(name);
        }
        final Category savedCategory = categoryRepo.save(category);
        return ResponseEntity.ok(savedCategory);
    }

}