package management.iconic.iconicmx.role.controller;

import management.iconic.iconicmx.role.model.Role;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = {"*"})
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<Message> getAll(){
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable long id){
        return roleService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody RoleDTO role){
        return roleService.save(new Role(role.getName()));
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody RoleDTO role){
        Role role1 = new Role(role.getName());
        return roleService.update(role1);
    }
}
