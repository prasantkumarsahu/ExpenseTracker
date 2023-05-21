package com.prasant.expenseTracker.services;

import com.prasant.expenseTracker.dtos.ProductInput;
import com.prasant.expenseTracker.dtos.ProductOutput;
import com.prasant.expenseTracker.models.Product;
import com.prasant.expenseTracker.models.User;
import com.prasant.expenseTracker.repositories.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductService {
    @Autowired
    private IProductRepo productRepo;

    public String addExpense(User user, ProductInput productInput) {
        Product product = new Product(productInput.getTitle(), productInput.getDescription(), productInput.getPrice(), user);
        productRepo.save(product);
        return "Product added to the expense";
    }

    public List<ProductOutput> getAllExpenses(User user) {
        List<Product> products = productRepo.findByUser(user);
        List<ProductOutput> productOutputs = new ArrayList<>();

        products.forEach(
                product -> productOutputs.add(new ProductOutput(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getDate(), product.getTime()))
        );

        return productOutputs;
    }

    public String deleteExpense(Long productId) {
        productRepo.deleteById(productId);
        return "Expense deleted successfully!";
    }

    public String update(Long productId, ProductInput productInput) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product == null){
            return "Invalid productId";
        }

        product.setTitle(productInput.getTitle());
        product.setDescription(productInput.getDescription());
        product.setPrice(productInput.getPrice());

        productRepo.save(product);
        return "Update successful!";
    }

    public List<ProductOutput> getAllExpensesByDate(User user, String date) {
        List<Product> products = productRepo.findByUser(user);
        List<ProductOutput> productOutputs = new ArrayList<>();

        products.forEach(
                product -> {
                    if (product.getDate().toString().equals(date))
                        productOutputs.add(new ProductOutput(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getDate(), product.getTime()));
                }
        );

        return productOutputs;
    }

    public Integer getTotalExpenseMonth(User user, Integer month) {
        List<Product> products = productRepo.findByUser(user);
        Integer totalExpense = 0;

        for (Product product : products){
            if (product.getDate().getMonthValue() == month)
                totalExpense += product.getPrice();
        }

        return totalExpense;
    }
}
