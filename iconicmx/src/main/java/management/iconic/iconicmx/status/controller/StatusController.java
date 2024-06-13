package management.iconic.iconicmx.status.controller;

import management.iconic.iconicmx.status.model.Status;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = {"*"})
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping("/all")
    public ResponseEntity<Message> getAll(){
        return statusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable long id){
        return statusService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody StatusDTO status){
        return statusService.save(new Status(status.getDescription()));
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody StatusDTO status){
        Status status1 = new Status(status.getDescription());
        return statusService.update(status1);
    }
}
