package br.com.tisemcensura.fullstack.controller;

import br.com.tisemcensura.fullstack.entities.Users;
import br.com.tisemcensura.fullstack.repositories.UserRepository;
import br.com.tisemcensura.fullstack.security.JwtTokenUtil;
import br.com.tisemcensura.fullstack.security.JwtUserDetailService;
import br.com.tisemcensura.fullstack.security.requests.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class JwtAuthenticateController {

    private List<Users> users = new ArrayList<Users>();

    private String token;

    private Long id;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUserDetailService  jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin
    @RequestMapping(value= "/login", method = RequestMethod.POST)
    public Optional<Users> createAuthenticateToken(@RequestBody JwtRequest authenticateValue)
            throws Exception {
        users = repository.findAll();
        for (Users user: users){
            if(user.getUsername().equals(authenticateValue.getUsername())){
                authenticate(authenticateValue.getUsername(), authenticateValue.getPassword());
                final UserDetails userDetails = jwtUserDetailService
                        .loadUserByUsername(authenticateValue.getUsername());
                this.token = jwtTokenUtil.generateToken(userDetails);

                this.id = user.getId();
            }
        }
        Optional<Users> obj = null;
        obj = repository.findById(this.id);
        obj.orElseThrow(() -> new Exception("Fail clean password")).setPassword("");
        obj.orElseThrow(() -> new Exception("Token not found")).setToken(token);
        return obj;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
