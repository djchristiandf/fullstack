package br.com.tisemcensura.fullstack.controller;


import br.com.tisemcensura.fullstack.entities.Users;
import br.com.tisemcensura.fullstack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        List<Users> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable Long id){
        ResponseEntity<?> list = userService.findById(id);
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users product){
        product = userService.create(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @CrossOrigin
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<Users> update(@RequestBody Users user, @PathVariable Long id){
        user = userService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
