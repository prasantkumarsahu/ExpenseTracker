package com.prasant.expenseTracker.services;

import com.prasant.expenseTracker.dtos.SigninInput;
import com.prasant.expenseTracker.dtos.SigninOutput;
import com.prasant.expenseTracker.dtos.SignupInput;
import com.prasant.expenseTracker.models.AuthToken;
import com.prasant.expenseTracker.models.User;
import com.prasant.expenseTracker.repositories.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private AuthTokenService authTokenService;

    public String registerUser(SignupInput signupInput) {
        boolean isRegistered = userRepo.existsByEmail(signupInput.getEmail());
        if (isRegistered){
            return "Email already registered. Sign in instead!";
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signupInput.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        User user = new User(signupInput.getFirstName(), signupInput.getLastName(), signupInput.getEmail(), encryptedPassword);
        userRepo.save(user);
        return "User is registered!";
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digested = md5.digest();
        return DatatypeConverter.printHexBinary(digested);
    }

    public SigninOutput signInUser(SigninInput signinInput) {
        User user = userRepo.findByEmail(signinInput.getEmail());
        if (user == null){
            return new SigninOutput("User doesn't exist! Register instead.", null);
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signinInput.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if (!encryptedPassword.equals(user.getPassword())){
            return new SigninOutput("Invalid user details!", null);
        }

        AuthToken token = new AuthToken(user);
        authTokenService.saveToken(token);
        return new SigninOutput("User signed in successful!", token.getToken());
    }

    public String signOutUser(String token) {
        return authTokenService.deleteToken(token);
    }

    public User getUserByToken(String token) {
        AuthToken authToken = authTokenService.getAuthTokenByToken(token);
        if (authToken == null){
            return null;
        }
        return authToken.getUser();
    }

    public boolean validateToken(String token) {
        return authTokenService.authenticate(token);
    }
}
