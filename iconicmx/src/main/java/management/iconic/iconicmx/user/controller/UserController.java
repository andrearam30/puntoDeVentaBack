package management.iconic.iconicmx.user.controller;

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

    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id){
        return userService.findById(id);
    }
}
