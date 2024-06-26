package management.iconic.iconicmx.user.controller;

import management.iconic.iconicmx.user.model.User;
import management.iconic.iconicmx.user.model.UserRepository;
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
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        return new ResponseEntity<>(new Message("OK", false, userRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id) {
        if (userRepository.existsById(id))
            return new ResponseEntity<>(new Message("OK", false, userRepository.findById(id)), HttpStatus.OK);
        return new ResponseEntity<>(new Message("El usuario no existe", true, null), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new Message("El usuario ya existe", true, null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message("OK", false, userRepository.saveAndFlush(user)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(User user) {
        if (userRepository.existsById(user.getId()))
            return new ResponseEntity<>(new Message("El usuario no existe", true, null), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Message("OK", false, userRepository.saveAndFlush(user)), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }
}