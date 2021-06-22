# api-cash-online
Creación de API para el challenge de backend de [Cash-Online](https://www.cash-online.com.ar/) utilizando SpringBoot 5, MySQL, Java

Para lograr su funcionamiento, los pasos ha realizar:

# Paso 0

Para poder ejecutar la API se debe crear previamente unas Base de Datos MYSQL con los siguientes comandos.
```
  CREATE DATABASE db_backend_cash;
  
```

# Paso 1

Dentro de `resources` se encuentra el `application.properties` donde esta la configuracion para conectarse con la Base de Datos.

# Paso 2

Creo mis dos entidades para que estas esten mapeadas a la BD
Dentro del package base `src/main/java` creo una carpeta entity, donde van a ir las dos entidades `User` y `Loan` y asigno sus atributos, creando sus getters and setters
```
  public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Loan> loans = new ArrayList<>();
  }
  
```
```
  public class User {
    private int id;
    private int total;
    private int user;
  }
  
```

# Paso 3

Creo el package `DAO` (Data Access Object).

Dentro creo un interfaz DAO para cada entity las cuales fueron `IUserDao`, `ILoanDao`.

# Paso 4
Cree el package `Service` donde voy a establecer dos interfaces `IUserService`, `ILoanService` donde a cada una le asigno los metodos del CRUD (Create, Read, Update, Delete).

```
public interface IUserService {

    public List<User> findAll();

    public User findById(int id);

    public User save (User user);

    public void delete(int id);

}

```
```
public interface ILoanService {
    
    
    public Page<Loan> findAll(Pageable pageable);

    public Page<Loan> findLoanByUserId(Integer id, Pageable pageable);

    public Loan findById(int id);

    public Loan save (Loan loan);

    public void delete(int id);

}
```
# Paso 5
En la misma package creo las clases `UserService`, `LoanService` para implementar las interfaces anteriormente creadas.
```
@Service
public class UserService implements IUserService{
  ...
}
```
Lo mismo para `LoanService`.

# Paso 6
Creo un package `Controller` donde procedo a crear `UserRestController`, `LoanRestController` y mapeo estos mismos,
```
@RestController
@RequestMapping("/api/v1")
public class LoanRestController {
  ...
}

```

```
@RestController
@RequestMapping("/api/v1")
public class UserRestController {
  ...
}
```
Dentro de estos creo los metodos `GET`, `POST`, `PUT`, `DELETE` con sus respectivos mapeos.

Se cuenta con un `LoanDto` en la carpeta `controller.dto` que se utilizo para la creacion de un `Loan` a traves de un `user_id`.

# Paso 7

Link a la colleccion de [Postman](https://www.getpostman.com/collections/963064d093c4e9025824)