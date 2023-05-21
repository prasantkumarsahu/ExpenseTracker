package com.prasant.expenseTracker.repositories;

import com.prasant.expenseTracker.models.AuthToken;
import org.springframework.data.repository.ListCrudRepository;

public interface IAuthTokenRepo extends ListCrudRepository<AuthToken, Long> {
    AuthToken findByToken(String token);

    boolean existsByToken(String token);
}
