package management.iconic.iconicmx.user.controller;

import management.iconic.iconicmx.user.model.User;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<Message> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id){
        return userService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody UserDTO userDTO){
        return userService.save(new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getRoles()));
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody UserDTO userDTO){
        return userService.update(new User(userDTO.getId(), userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getRoles()));
    }
}
