package com.prasant.expenseTracker.controllers;

import com.prasant.expenseTracker.dtos.ProductInput;
import com.prasant.expenseTracker.dtos.ProductOutput;
import com.prasant.expenseTracker.dtos.TotalExpenditureOutput;
import com.prasant.expenseTracker.models.Product;
import com.prasant.expenseTracker.models.User;
import com.prasant.expenseTracker.services.ProductService;
import com.prasant.expenseTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @PostMapping("/{token}")
    public ResponseEntity<String> addExpense(@PathVariable String token, @RequestBody ProductInput productInput){
        User user = userService.getUserByToken(token);
        if (user == null){
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(productService.addExpense(user, productInput), HttpStatus.CREATED);
    }

    @GetMapping("{token}")
    public ResponseEntity<Object> getAllExpense(@PathVariable String token){
        User user = userService.getUserByToken(token);
        if (user == null){
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

        List<ProductOutput> productOutputs = productService.getAllExpenses(user);

        if (productOutputs.size() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productOutputs, HttpStatus.OK);
    }

    @GetMapping("all/date/{date}/{token}")
    public ResponseEntity<Object> getAllExpenseByDate(@PathVariable String date, @PathVariable String token){
        User user = userService.getUserByToken(token);
        if (user == null){
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

        List<ProductOutput> productOutputs = productService.getAllExpensesByDate(user, date);

        if (productOutputs.size() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productOutputs, HttpStatus.OK);
    }

    @GetMapping("total/month/{month}/{token}")
    public ResponseEntity<Object> getTotalExpenseMonth(@PathVariable String token, @PathVariable Integer month){
        User user = userService.getUserByToken(token);
        if (user == null){
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

        Integer total = productService.getTotalExpenseMonth(user, month);

        return new ResponseEntity<>(new TotalExpenditureOutput(total), HttpStatus.OK);
    }

    @PutMapping("{productId}/{token}")
    public ResponseEntity<String> updateExpense(@PathVariable Long productId, @PathVariable String token, @RequestBody ProductInput productInput){
        boolean isTokenValid = userService.validateToken(token);
        if (!isTokenValid) {
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(productService.update(productId, productInput), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("{productId}/{token}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long productId, @PathVariable String token){
        boolean isTokenValid = userService.validateToken(token);
        if (!isTokenValid) {
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(productService.deleteExpense(productId), HttpStatus.NOT_FOUND);
    }
}
