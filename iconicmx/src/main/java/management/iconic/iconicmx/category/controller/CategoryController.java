package management.iconic.iconicmx.category.controller;

import management.iconic.iconicmx.category.model.Category;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  categoryService.findAll();
    }

    @GetMapping("/{category}")
    public ResponseEntity<Message> getById(@PathVariable("category") long id){
        return categoryService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody CategoryDTO category){
        Category saveCategory = new Category(category.getName(), category.getDescription(), category.getStatus());
        return categoryService.save(saveCategory);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody CategoryDTO categoryDTO){
        Category updateCategory = new Category(categoryDTO.getName(), categoryDTO.getDescription(), categoryDTO.getStatus());
        updateCategory.setId(categoryDTO.getId());
        return categoryService.update(updateCategory);
    }
}
