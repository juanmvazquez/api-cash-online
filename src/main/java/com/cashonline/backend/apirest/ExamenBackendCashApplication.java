package com.cashonline.backend.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // es la mas importante porque tiene @sprintboot configuration, @EnableAutoCfg,  que es la cfg automatica que se puede poner en el properties.@ComponentScan regista en el contenedor de spring todas las clases anotadas @restcontroller @controller etc
public class ExamenBackendCashApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenBackendCashApplication.class, args);
    }
    
}
