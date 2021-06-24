package com.cashonline.backend.apirest;

import com.cashonline.backend.apirest.controllers.LoanRestController;
import com.cashonline.backend.apirest.controllers.exceptions.NotFoundUserException;
import com.cashonline.backend.apirest.controllers.UserRestController;
import com.cashonline.backend.apirest.controllers.dto.UserDto;
import com.cashonline.backend.apirest.models.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRestControllerTests {

  @Autowired
  UserRestController userRestController;

  @Autowired
  LoanRestController loanRestController;

  @Test
  void contextUser() {
    assertThat(userRestController).isNotNull();
  }

  @Test
  void contextLoans() {
    assertThat(loanRestController).isNotNull();
  }


  @Test
  void createUser() {
    User user = new User();
    user.setFirsName("Juan Manuel");
    user.setLastName("Vazquez");
    user.setEmail("juanmanuelvazquez12@gmail.com");
    userRestController.create(user);
    UserDto userpersistence = userRestController.show(5);

    Assertions.assertEquals(user.getFirstName(), userpersistence.getFirstName());
    Assertions.assertEquals(user.getLastName(), userpersistence.getLastName());
    Assertions.assertEquals(user.getEmail(), userpersistence.getEmail());

  }


  @Test
  void deleteUser() {
    User user = new User();
    user.setFirsName("Juan Manuel");
    user.setLastName("Vazquez");
    user.setEmail("juanmanuelvazquez12@gmail.com");
    userRestController.create(user);
    UserDto userpersistence = userRestController.show(5);
    Assertions.assertNotEquals(userpersistence, null);

    userRestController.delete(5);
    Exception exception = Assertions.assertThrows(NotFoundUserException.class,() -> userRestController.show(5)  );
    Assertions.assertEquals("ERROR El id no existe en la base.",exception.getMessage());
  }

}

