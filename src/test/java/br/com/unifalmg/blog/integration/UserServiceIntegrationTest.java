package br.com.unifalmg.blog.integration;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Test
    @DisplayName("#FindById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException(){

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser(){
        User user = service.findById(16);
        Assertions.assertAll(
                () -> Assertions.assertEquals(16,user.getId()),
                () -> Assertions.assertEquals("Vinicius Júnior", user.getName()),
                () -> Assertions.assertEquals("Malvadeza", user.getUsername()),
                () -> Assertions.assertEquals("vini.jr@madrid.com", user.getEmail()),
                () -> Assertions.assertEquals("12345678901011121314", user.getPhone()),
                () -> Assertions.assertEquals("https://institutovinijr.org.br", user.getWebsite())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException(){

        Assertions.assertThrows(UserNotFoundException.class, () ->
                service.findById(80));
    }
}
