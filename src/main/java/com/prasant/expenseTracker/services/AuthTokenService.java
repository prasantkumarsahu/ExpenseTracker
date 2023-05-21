package com.prasant.expenseTracker.services;

import com.prasant.expenseTracker.models.AuthToken;
import com.prasant.expenseTracker.repositories.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    @Autowired
    private IAuthTokenRepo authTokenRepo;

    public void saveToken(AuthToken token) {
        authTokenRepo.save(token);
    }

    public String deleteToken(String token) {
        AuthToken authToken = authTokenRepo.findByToken(token);
        if (authToken == null){
            return "Invalid token!";
        }

        authTokenRepo.delete(authToken);
        return "Signed out successful!";
    }

    public AuthToken getAuthTokenByToken(String token) {
        return authTokenRepo.findByToken(token);
    }

    public boolean authenticate(String token) {
        return authTokenRepo.existsByToken(token);
    }
}
