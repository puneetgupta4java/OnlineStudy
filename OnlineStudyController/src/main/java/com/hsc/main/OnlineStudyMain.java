package com.hsc.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"com.hsc"})
@EnableJpaRepositories(basePackages={"com.hsc.repositories"})
@EnableAutoConfiguration
@EntityScan(basePackages={"com.hsc.entities"})
public class OnlineStudyMain {
public static void main(String[] args) throws Exception {
	SpringApplication.run(OnlineStudyMain.class, args);
}

}
