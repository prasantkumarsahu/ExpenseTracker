# Expense Tracker
This is a API project for `User`s to manage their expenses/`Product`s. `User` can create, read, update, and delete his/her `Product`s to manage their money.

---
### Try Link (AWS Deployment - Swagger UI)
    `http://ec2-15-206-30-98.ap-south-1.compute.amazonaws.com:8080/swagger-ui/index.html`
---

![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot "Spring Boot") ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white "Java") ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white "Postman") ![Google Chrome](https://img.shields.io/badge/Google%20Chrome-4285F4?style=for-the-badge&logo=GoogleChrome&logoColor=white "Google Chrome")

## Frameworks and Languages
![Java v17](https://img.shields.io/badge/Java-v17-green "Java 17") ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v3.0.7-brightgreen "Spring Boot v3.0.7")

---
## Browser / Tools
![Google Chrome](https://img.shields.io/badge/Google%20Chrome-v112.0.5615.138-yellow "Google Chrome") ![Swagger](https://img.shields.io/badge/Swagger-v3-brightgreen "Swagger") ![Postman](https://img.shields.io/badge/Postman-v10.13.0-orange "Postman")
---

## Model
- ### User
    - ```java
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      ```
    - ```java
      private String firstName;
      ```
    - ```java
      private String lastName;
      ```
    - ```java
      @Column(unique = true)
      private String email;
      ```
    - ```java
      private String password;
      ```
- ### Product
    - ```java
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      ```
    - ```java
      private String title;
      ```
    - ```java
      private String description;
      ```
    - ```java
      private Integer price;
      ```
    - ```java
      private LocalDate date;
      ```
    - ```java
      private LocalTime time;
      ```
    - ```java
      @ManyToOne
      private User user;
      ```
- ### AuthToken
    - ```java
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      ```
    - ```java
      @Column(unique = true)
      private String token;
      ```
    - ```java
      private LocalDate date;
      ```
    - ```java
      @OneToOne
      private User user;
      ```
---
## Dataflow
- ### End Points / Controllers
  - #### _User_ `@RequestMapping("user")`
    ```java
    @PostMapping("signup")
    public String signupUser(@Valid @RequestBody SignupInput signupInput)
    ```
    ```java
    @PostMapping("signin")
    public SigninOutput signinUser(@Valid @RequestBody SigninInput signinInput)
    ```
    ```java
    @DeleteMapping("signout/{token}")
    public String signoutUser(@PathVariable String token)
    ```
  - #### _Product_ `@RequestMapping("expense")`
    ```java
    @PostMapping("/{token}")
    public ResponseEntity<String> addExpense(@PathVariable String token, @RequestBody ProductInput productInput)
    ```
    ```java
    @GetMapping("{token}")
    public ResponseEntity<Object> getAllExpense(@PathVariable String token)
    ```
    ```java
    @GetMapping("all/date/{date}/{token}")
    public ResponseEntity<Object> getAllExpenseByDate(@PathVariable String date, @PathVariable String token)
    ```
    ```java
    @GetMapping("total/month/{month}/{token}")
    public ResponseEntity<Object> getTotalExpenseMonth(@PathVariable String token, @PathVariable Integer month)
    ```
    ```java
    @PutMapping("{productId}/{token}")
    public ResponseEntity<String> updateExpense(@PathVariable Long productId, @PathVariable String token, @RequestBody ProductInput productInput)
    ```
    ```java
    @DeleteMapping("{productId}/{token}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long productId, @PathVariable String token)
    ```
- ### Services
  - #### _User_
    ```java
    public String registerUser(SignupInput signupInput)
    ```
    ```java
    private String encryptPassword(String password) throws NoSuchAlgorithmException
    ```
    ```java
    public SigninOutput signInUser(SigninInput signinInput)
    ```
    ```java
    public String signOutUser(String token)
    ```
    ```java
    public User getUserByToken(String token)
    ```
    ```java
    public boolean validateToken(String token)
    ```
  - #### _Product_
    ```java
    public String addExpense(User user, ProductInput productInput)
    ```
    ```java
    public List<ProductOutput> getAllExpenses(User user)
    ```
    ```java
    public String deleteExpense(Long productId)
    ```
    ```java
    public String update(Long productId, ProductInput productInput)
    ```
    ```java
    public List<ProductOutput> getAllExpensesByDate(User user, String date)
    ```
    ```java
    public Integer getTotalExpenseMonth(User user, Integer month)
    ```
  - #### _AuthTokenService_
    ```java
    public void saveToken(AuthToken token)
    ```
    ```java
    public String deleteToken(String token)
    ```
    ```java
    public AuthToken getAuthTokenByToken(String token)
    ```
    ```java
    public boolean authenticate(String token)
    ```
- ### Repository
    - _User_
        ```java
        public interface IUserRepo extends ListCrudRepository<User, Long> {
          boolean existsByEmail(String email);

          User findByEmail(String email);
        }
        ```
    - _Product_
        ```java
        public interface IProductRepo extends ListCrudRepository<Product, Long> {
          List<Product> findByUser(User user);

        }
        ```
    - _AuthToken_
        ```java
        public interface IAuthTokenRepo extends ListCrudRepository<AuthToken, Long> {
          AuthToken findByToken(String token);

          boolean existsByToken(String token);
        }
        ```
- ### Database
    I have used `MySQL` database in this project. And used `SpringDataJPA`.
    - #### Design / Relationship
      ```
      `AuthToken` (OneToOne) ---> `User` 
      `Product` (ManyToOne) ---> `User` 
      ```
---
## Datastructures
- `ArrayList<>`
---
## Summary
This API is a `Spring Boot` project that is about Users managing their expenses. `User` can create, read, update, and delete their expenses, those operations are also using authentication for `User`. In this project request is sent from the client on HTTP in JSON body or from path variable and stored in object and performed business logic or perferm operation then response is sent back to the client from the server in JSON format over HTTP.

