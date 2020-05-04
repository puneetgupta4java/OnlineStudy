package com.adda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {DemoApp.class})
@EnableJpaRepositories(basePackages={"com.adda.repository"})
@EnableAutoConfiguration
@EntityScan(basePackages={"com.adda.entity"})
public class DemoApp {

    public static void main(String[] args) {

        SpringApplication.run(DemoApp.class, args);
    }

}
