package com.demo3.demo3.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path="/all")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path="/get/{id}")
    public Optional<Category> findCategoryById(@PathVariable("id") Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category ;
        }else{
            throw new Exception("No category with this id");
        }
    }

    @PostMapping(path="/add")
    public @ResponseBody void addCategory(@RequestParam String name) throws Exception {
        Category a = new Category(name);
        if(categoryRepository.findCategoryByName(name).isPresent()){
            throw new Exception("Category with this name already exists");
        }else{
            categoryRepository.save(a);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id) throws Exception {
        Optional<Category> check = findCategoryById(id);
        if(check.isPresent()){
            categoryRepository.deleteById(id);
        }else{
            throw new Exception("No category with this id");
        }
    }

    @Transactional
    @PutMapping("/modify/{id}")
    public void modifyCategoryById(@PathVariable("id") Long id, @RequestParam String name){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("id not found: " + id));
        category.setName(name);
    }

}