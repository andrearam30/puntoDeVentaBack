package management.iconic.iconicmx.role.controller;

import management.iconic.iconicmx.role.model.Role;
import management.iconic.iconicmx.role.model.RoleRepository;
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
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("OK", roleRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional<Role> existsRole = roleRepository.findById(id);
        if (existsRole.isPresent()){
            return new ResponseEntity<>(new Message("OK", roleRepository.getById(id)), HttpStatus.OK);
        }
        return  new ResponseEntity<>(new Message("El rol no existe", null), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Role role){
        Optional<Role> foundRole = roleRepository.findByName(role.getName());
        if (foundRole.isPresent()){
            return  new ResponseEntity<>(new Message("El rol ya existe", null), HttpStatus.BAD_REQUEST);
        }
        Role savedRole = roleRepository.saveAndFlush(role);
        return new ResponseEntity<>(new Message("OK", savedRole), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Role role){
        if (roleRepository.existsById(role.getId())){
            Role updateRole = roleRepository.saveAndFlush(role);
            return new ResponseEntity<>(new Message("OK", updateRole), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El rol no existe", null), HttpStatus.BAD_REQUEST);
    }
}
