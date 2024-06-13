package management.iconic.iconicmx.subcategory.controller;

import management.iconic.iconicmx.subcategory.model.Subcategory;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subcategory")
@CrossOrigin(origins = {"*"})
public class SubcategoryController {
    @Autowired
    SubcategoryService subcategoryService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return  subcategoryService.findAll();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Message> getAllByCategory(@PathVariable long id){
        return subcategoryService.findByCategory(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id) {
        return subcategoryService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody SubcategoryDTO subcategoryDTO) {
        return subcategoryService.save(new Subcategory(subcategoryDTO.getName(), subcategoryDTO.getDescription(), subcategoryDTO.getCategory()));
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody SubcategoryDTO subcategoryDTO) {
        Subcategory updateSubcategory = new Subcategory(subcategoryDTO.getName(), subcategoryDTO.getDescription(), subcategoryDTO.getCategory());
        updateSubcategory.setId(subcategoryDTO.getId());
        return subcategoryService.update(updateSubcategory);
    }
}
