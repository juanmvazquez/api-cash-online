package com.cashonline.backend.apirest;

import com.cashonline.backend.apirest.controllers.LoanRestController;
import com.cashonline.backend.apirest.controllers.exceptions.NotFoundUserException;
import com.cashonline.backend.apirest.controllers.UserRestController;
import com.cashonline.backend.apirest.controllers.dto.UserDto;
import com.cashonline.backend.apirest.models.dao.IUserDao;
import com.cashonline.backend.apirest.models.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.transaction.Transactional;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRestControllerTests {

  @Autowired
  UserRestController userRestController;

  @Autowired
  LoanRestController loanRestController;

  @Autowired
  private IUserDao userDao;

  @MockBean
  private BindingResult bindingResult;;

  @Test
  void contextUser() {
    assertThat(userRestController).isNotNull();
  }

  @Test
  void contextLoans() {
    assertThat(loanRestController).isNotNull();
  }


  @Test
  @Transactional
  @Rollback
  void createUser() {
    User user = new User();
    user.setFirsName("Juan Manuel");
    user.setLastName("Vazquez");
    user.setEmail("juanmanuelvazquez12@gmail.com");
    userRestController.create(user, bindingResult);
    UserDto userpersistence = userRestController.show(5);

    Assertions.assertEquals(user.getFirstName(), userpersistence.getFirstName());
    Assertions.assertEquals(user.getLastName(), userpersistence.getLastName());
    Assertions.assertEquals(user.getEmail(), userpersistence.getEmail());

  }

  @Test
  @Transactional
  @Rollback
  void deleteUser() {
    User user = new User();
    user.setFirsName("Juan Manuel");
    user.setLastName("Vazquez");
    user.setEmail("juanmanuelvazquez12@gmail.com");
    userRestController.create(user, bindingResult);
    UserDto userpersistence = userRestController.show(6);
    Assertions.assertNotEquals(userpersistence, null);

    userRestController.delete(6);
    Exception exception = Assertions.assertThrows(NotFoundUserException.class,() -> userRestController.show(5)  );
    Assertions.assertEquals("ERROR El id no se encuentra en la DB",exception.getMessage());
  }


  @Test
  @Transactional
  @Rollback
  public void createUserDao() {
    User user = new User("Juan", "Vazquez", "juanmanuelvazquez12@gmail.com");
    User newUser = userDao.save(user);

    assertNotNull(newUser);
  }

  @Test
  @Transactional
  @Rollback
  void deleteUserDao() {
    Integer id = 1;

    boolean isExistBeforeDelete = userDao.findById(id).isPresent();
    userDao.deleteById(id);
    boolean notExistAfterDelete = userDao.findById(id).isPresent();
    assertTrue(isExistBeforeDelete);
    assertFalse(notExistAfterDelete);
  }

}



