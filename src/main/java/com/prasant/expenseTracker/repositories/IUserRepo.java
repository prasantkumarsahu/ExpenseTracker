package com.prasant.expenseTracker.repositories;

import com.prasant.expenseTracker.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface IUserRepo extends ListCrudRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
