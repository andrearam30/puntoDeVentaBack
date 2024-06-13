package management.iconic.iconicmx.category.controller;

import management.iconic.iconicmx.category.model.Category;
import management.iconic.iconicmx.category.model.CategoryRepository;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        return new ResponseEntity<>(new Message("OK", categoryRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id) {
        ResponseEntity<Message> response = null;
        if (categoryRepository.existsById(id)) {
            response = new ResponseEntity<>(new Message("Categoría encontrada", categoryRepository.findById(id)), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(new Message("La categoría no se encontró", null), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if (optionalCategory.isPresent()) {
            return new ResponseEntity<>(new Message("La categoría ya existe", null), HttpStatus.BAD_REQUEST);
        }
        Category category1 = categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(new Message("OK", category1), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            Category category1 = categoryRepository.saveAndFlush(category);
            return new ResponseEntity<>(new Message("OK", category1), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("La categoría no existe", null), HttpStatus.BAD_REQUEST);
    }

}
