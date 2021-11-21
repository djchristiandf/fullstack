package br.com.tisemcensura.fullstack.controller;

import br.com.tisemcensura.fullstack.entities.Users;
import br.com.tisemcensura.fullstack.repositories.UserRepository;
import br.com.tisemcensura.fullstack.security.JwtTokenUtil;
import br.com.tisemcensura.fullstack.security.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class JwtAuthenticateController {

    private List<Users> users = new ArrayList<Users>();

    private String token;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUserDetailService  jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @CrossOrigin
    @RequestMapping(value= "/login", method = RequestMethod.POST)
    public String createAuthenticateToken(@RequestBody Users authenticateValue){
        users = repository.findAll();
        for (Users user: users){
            if(user.getUsername().equals(authenticateValue.getUsername())
                && passwordEncoder.matches(authenticateValue.getPassword(), user.getPassword())){
                final UserDetails userDetails = jwtUserDetailService
                        .loadUserByUsername(authenticateValue.getUsername());
                this.token = jwtTokenUtil.generateToken(userDetails);

                return this.token;
            }
        }
        return "Error";
    }

}
