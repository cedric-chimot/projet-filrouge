package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.services.impl.BootcampServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;



import java.util.List;

@RestController
@RequestMapping("/bootcamps")
@CrossOrigin
public class BootcampController {

    private final BootcampServiceImpl bootcampService;

    public BootcampController(BootcampServiceImpl bootcampService) {
        this.bootcampService = bootcampService;
    }

    @GetMapping("/all")
    public List<Bootcamp> findAll(){
        return bootcampService.findAll();
    }

    @GetMapping("/find/{id}")
    public Bootcamp findById(@PathVariable long id){
        return bootcampService.findById(id);
    }

    @PostMapping("/create")
    public Bootcamp createBootcamp(@Validated @RequestBody Bootcamp bootcamp){
        return bootcampService.create(bootcamp);
    }

    @PostMapping("/createBootcamps")
    public List<Bootcamp> listBootcamps(@RequestBody List<Bootcamp> bootcamps) {
        return bootcampService.createBootcamps(bootcamps);
    }

    @PostMapping("/bootcamps/{bootcampId}/addUser")
    public ResponseEntity<String> addUserToBootcamp(@PathVariable long bootcampId, @RequestBody Users user) {
        bootcampService.addUserBootcamp(bootcampId, user);
        return new ResponseEntity<>("User ajouté au bootcamp", HttpStatus.OK);
    }

    @PatchMapping("/update")
    public void update(@RequestBody Bootcamp bootcamp){
        bootcampService.update(bootcamp);
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        bootcampService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        bootcampService.deleteById(id);
    }

}