package com.prasant.expenseTracker.controllers;

import com.prasant.expenseTracker.dtos.SigninInput;
import com.prasant.expenseTracker.dtos.SigninOutput;
import com.prasant.expenseTracker.dtos.SignupInput;
import com.prasant.expenseTracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public String signupUser(@Valid @RequestBody SignupInput signupInput){
        return userService.registerUser(signupInput);
    }

    @PostMapping("signin")
    public SigninOutput signinUser(@Valid @RequestBody SigninInput signinInput){
        return userService.signInUser(signinInput);
    }

    @DeleteMapping("signout/{token}")
    public String signoutUser(@PathVariable String token){
        return userService.signOutUser(token);
    }
}
