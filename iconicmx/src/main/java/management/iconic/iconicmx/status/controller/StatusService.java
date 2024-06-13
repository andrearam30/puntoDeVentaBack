package management.iconic.iconicmx.status.controller;

import management.iconic.iconicmx.role.model.Role;
import management.iconic.iconicmx.status.model.Status;
import management.iconic.iconicmx.status.model.StatusRepository;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        return new ResponseEntity<>(new Message("OK", statusRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional<Status> existsStatus = statusRepository.findById(id);
        if (existsStatus.isPresent()){
            return new ResponseEntity<>(new Message("OK", statusRepository.getById(id)), HttpStatus.OK);
        }
        return  new ResponseEntity<>(new Message("El status no existe", null), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Status status){
        Optional<Status> foundStatus = statusRepository.findByDescription(status.getDescription());
        if (foundStatus.isPresent()){
            return  new ResponseEntity<>(new Message("El status ya existe", null), HttpStatus.BAD_REQUEST);
        }
        Status savedStatus = statusRepository.saveAndFlush(status);
        return new ResponseEntity<>(new Message("OK", savedStatus), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Status status){
        if (statusRepository.existsById(status.getId())){
            Status updateStatus = statusRepository.saveAndFlush(status);
            return new ResponseEntity<>(new Message("OK", updateStatus), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El status no existe", null), HttpStatus.BAD_REQUEST);
    }
}
