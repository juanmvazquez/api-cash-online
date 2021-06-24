# api-cash-online
Creación de API para el challenge de backend de [Cash-Online](https://www.cash-online.com.ar/) utilizando SpringBoot 5, MySQL, Java

Para lograr su funcionamiento, los pasos ha realizar:

# Paso 0
Dentro de `resources` se encuentra el `application.properties` donde esta la configuracion para conectarse con la Base de Datos.
 
# Paso 1

Realice una migracion de la base de datos con flyway agregando `spring.flyway.baseline-on-migrate=true` al `application properties` .
Para poder correr la API es necersaio ejecutar dentro de MySQL la siguiente linea.
```
    CREATE DATABASE db_backend_cash;  
```
# Paso 2

Dentro del package `resources.db.migration` donde estan los files.sql a utilizar `V1__usersTable.sql`,`V2__loansTable.sql` y un tercero para proceder a la carga de datos `V3__insertData.sql`.
```
CREATE TABLE db_backend_cash.user(
    ...)
```

```
CREATE TABLE db_backend_cash.loan (
    ...)
```

```
#Carga de user
INSERT INTO db_backend_cash.user (...);
#Carga de loan
INSERT INTO db_backend_cash.loan(...) VALUES(...);

```
# Paso 3

Existen dos entidades que se mapean con el modelo de la BD, dentro del package `src.main.java.entity`, donde van a ir las dos entidades, estas son `User` y `Loan` (con sus atributos, getters and setters).
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

# Paso 4

Para acceder a la base de datos se utilizan interfaces DAO que extienden a `JpaRepository`, las cuales son `IUserDao`, `ILoanDao`.

# Paso 5
Dentro del package `Services` almaceno las interfaces `IUserService`, `ILoanService` donde a cada una contiene los metodos del CRUD (Create, Read, Update, Delete).

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
# Paso 6
En el mismo package se encuentran  las clases `UserService`, `LoanService` que implementan las interfaces anteriormente creadas.
```
@Service
public class UserService implements IUserService{
  ...
}
```
IDEM `LoanService`.

# Paso 7
Dentro del package `Controller` se encuentran los controllers `UserRestController`, `LoanRestController` 
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
Quienes implementan los metodos HTTP : `GET`, `POST`, `PUT`, `DELETE` 
# Paso 8

En el package `controller.dto` se encuentran con los siguientes DTOs `UserDto`,`LoanDto`,`ItemDto`,`LoanResponseDto`,`PagingDto`.

`LoanDto` se utilizo para la creacion de un `Loan` a traves de un `user_id`.

`ItemDto` se utilizo para que se visualicen los items como se requiere.
```
{
...
"loans":[
    {
      "id":1,
      "total":2500,
      "userId":1
    }
  ]
}
```
`LoanResponseDto` & `PagingDto` se utilizaron para que cuando se ejecute el listado de loans me devuelva la paginacion, tal como se requiere.

```
{
...
"paging":{
           "page":1,
           "size":50,
           "total":1500
         }
}
```

# Paso 9

En el package `controllers.exceptions` se encuentran `ExceptionResponse`, `NotFoundUserException`, `NotFoundUserResponseEntityExceptionHandler`

`ExceptionResponse`  muesta el mensaje en la excepcion.
```
public class ExceptionResponse {

    private String message;
    
}
```

`NotFoundUserException` genera como respuesta de la API  el codigo de error (404) `HttpStatus.NOT_FOUND`

`NotFoundUserResponseEntityExceptionHandler` es el encargado de handlear la excepcion generada por la API.

# Paso 10
Dentro de la carpeta `test` se encuentra `UserRestControllerTest` donde se testearon las funcionalidades `createUser`, `deleteUser`.

FE DE ERRATAS : Quedo pendiende el testeo del `LoanRestController` y un alto coverage en el `UserRestController` debido al tiempo que me apremiaba.
```
@Test
  void createUser() {    
    ...
}
```
```
@Test
void deleteUser() {
    ...
}
```

# Paso 11 

Link a la colleccion de [Postman](https://www.getpostman.com/collections/963064d093c4e9025824)