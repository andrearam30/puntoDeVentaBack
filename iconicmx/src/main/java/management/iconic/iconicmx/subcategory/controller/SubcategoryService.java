package management.iconic.iconicmx.subcategory.controller;

import management.iconic.iconicmx.subcategory.model.Subcategory;
import management.iconic.iconicmx.subcategory.model.SubcategoryRepository;
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
public class SubcategoryService {
    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        return new ResponseEntity<>(new Message("OK", subcategoryRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findByCategory(long id){
        return new ResponseEntity<>(new Message("OK", subcategoryRepository.findAllByCategoryId(id)), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        ResponseEntity<Message> response = null;
        if (subcategoryRepository.existsById(id)){
            response = new ResponseEntity<>(new Message("OK", subcategoryRepository.findById(id)), HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(new Message("Error", null), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Subcategory subcategory){
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findByName(subcategory.getName());
        if (optionalSubcategory.isPresent()){
            return new ResponseEntity<>(new Message("Error", null), HttpStatus.BAD_REQUEST);
        }
        Subcategory subcategory1 = subcategoryRepository.saveAndFlush(subcategory);
        return new ResponseEntity<>(new Message("OK", subcategory1), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Subcategory subcategory){
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findByName(subcategory.getName());
        if (optionalSubcategory.isPresent()){
            Subcategory subcategory1 = subcategoryRepository.saveAndFlush(subcategory);
            return new ResponseEntity<>(new Message("OK", subcategory1), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("Error", null), HttpStatus.BAD_REQUEST);
    }

}
