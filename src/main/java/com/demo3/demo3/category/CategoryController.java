package com.demo3.demo3.category;

import com.demo3.demo3.actor.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping(path="/all")
    public Iterable<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @GetMapping(path="/byid/{id}")
    public Optional<Category> findCategoryById(@PathVariable("id") Long id){
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()){
            return category ;
        }else{
            throw new IllegalStateException("Category with Id: " + id + " does not exist");
        }
    }

    @DeleteMapping(path="/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        Optional<Category> check = findCategoryById(id);
        if(check.isPresent()){
            categoryRepo.deleteById(id);
        }else{
            throw new IllegalStateException("Category with Id: " + id + " does not exist");
        }
    }

    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void addCategory(@RequestBody Category category)                                {
        Optional<Category> categoryByName = categoryRepo.findCategoryByName(category.getName());
        if(categoryByName.isPresent()){
            throw new IllegalStateException("Category with name: " + category.getName() + " already exists" );
        }else{
            categoryRepo.save(category);
        }
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