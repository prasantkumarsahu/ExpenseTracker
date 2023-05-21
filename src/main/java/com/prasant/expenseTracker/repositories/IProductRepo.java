package com.prasant.expenseTracker.repositories;

import com.prasant.expenseTracker.models.Product;
import com.prasant.expenseTracker.models.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IProductRepo extends ListCrudRepository<Product, Long> {
    List<Product> findByUser(User user);
}
