package com.groupad.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupad.backend.model.Address;
import com.groupad.backend.model.User;
import com.groupad.backend.model.responses.LoginResponse;
import com.groupad.backend.model.responses.SignupResponse;
import com.groupad.backend.repository.AddressRepository;
import com.groupad.backend.repository.UserRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PutMapping;

import com.groupad.backend.bl.UserBL;
import com.groupad.backend.exception.UserExceptions.login.LoginException;
import com.groupad.backend.exception.UserExceptions.login.UserNotFoundException;
import com.groupad.backend.exception.UserExceptions.signup.AccountCreationException;

/**
 * @author Michelangelo Granato
 *
 */
@RestController
public class UserController {

    @Autowired
    private final UserRepository repository;

    @Autowired
    AddressRepository aRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private UserBL userBL;

    public UserController(UserRepository repository) {
        this.repository = repository;
        this.userBL = new UserBL(repository);
    }

    @CrossOrigin
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {

        LoginResponse response = new LoginResponse();
        try {
            response.setResult(userBL.login(user));

        } catch (LoginException e) {
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @CrossOrigin
    @PostMapping("/signup")
    public SignupResponse putNewUser(@RequestBody User newUser) throws AccountCreationException {
        SignupResponse response = new SignupResponse();
        try {
            response.setEmail(userBL.addNewUser(newUser).getEmail());

        } catch (AccountCreationException e) {
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @CrossOrigin
    @GetMapping("/users/isAdmin")
    public Boolean isAdmin(@RequestParam String email) {
        Optional<User> user = repository.findByEmail(email);
        return user.isPresent() && user.get().getAdmin() == 1;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{email}")
    public User findUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @PutMapping("/users/{email}")
    public User replaceUser(@RequestBody User newUser, @PathVariable String email) {

        return repository.findByEmail(email)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setFName(newUser.getFName());
                    user.setLName(newUser.getLName());
                    user.setAdmin(newUser.getAdmin());
                    user.setCheckoutCount(newUser.getCheckoutCount());

                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setEmail(email);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{email}")
    public void deleteUser(@PathVariable String email) {
        repository.deleteById(email);
    }

    @GetMapping("/users/testnames")
    public List<String> getAllUserName() {
        List<String> userNameList = new ArrayList<>();
        userNameList.addAll(jdbcTemplate.queryForList("select name from user", String.class));
        return userNameList;
    }

    // get all addresses saved for login user
    @GetMapping("/users/address")
    public List<Address> getAllAddress(@RequestParam String email) {
        List<Address> list = aRepo.findByEmail(email);
        return list;
    }

    @PostMapping("/users/address")
    public Address putAddress(@RequestBody Address address) {
        return aRepo.save(address);
    }

    // if user select one address among all address
    @GetMapping("/users/address/{address_no}")
    public Address getOneAddress(@PathVariable Long addressId) {
        Optional<Address> adrs = aRepo.findById(addressId);

        if (adrs.isEmpty())
            return null;
        return adrs.get();
    }
}