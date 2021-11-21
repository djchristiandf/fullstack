package br.com.tisemcensura.fullstack.services;

import br.com.tisemcensura.fullstack.entities.Users;
import br.com.tisemcensura.fullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String senhaComHash;


    @GetMapping
    public List<Users> findAll(){
        return repository.findAll();
    }

    public ResponseEntity<?> findById(long id){
        return repository.findById(id)
                .map( response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Users create(Users user){
        this.senhaComHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(this.senhaComHash);
        return repository.save(user);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    public Users update(Users user, long id){
         user.setId(id);
         return repository.save(user);
    }

    public ResponseEntity<?> delete(long id){
        repository.deleteById(id);
        return null;
    }
}
