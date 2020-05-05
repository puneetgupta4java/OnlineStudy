package com.adda;

import javax.transaction.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import com.adda.entity.Course;
import com.adda.repository.CourseRepository;

@SpringBootApplication
@ComponentScan(basePackageClasses = { DemoApp.class })
@EnableJpaRepositories(basePackages = { "com.adda.repository" })
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.adda.entity" })
public class DemoApp implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	public static void main(String[] args) {

		SpringApplication.run(DemoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		courseRepository.save(new Course(1L, "Core java", 3));
		courseRepository.save(new Course(2L, "advance java", 5));
		courseRepository.save(new Course(3L, "spring java", 10));
		courseRepository.save(new Course(4L, "spring boot", 5));
		courseRepository.save(new Course(5L, "hibernate and JPA", 3));
		//connect();
	}

	// This method can be used for testing the Redis connection
	public Status connect() {
		Status status = null;
		RedisConnection redisConnection = null;
		try {
			System.out.println("redisTemplate.getConnectionFactory() " + redisTemplate.getConnectionFactory());
			redisConnection = redisTemplate.getConnectionFactory().getConnection();
			if (redisConnection != null && !redisConnection.isClosed()) {
				System.out.println(redisConnection.ping());
				System.out.println("\n\n\n\nconnection success\n\n\n\n");
			} else {
				System.out.println("\n\n\n\nfail\n\n\n\n");
			}

		} catch (Exception exception) {
			System.out.println("\n\n\n\nexception\n\n\n\n" + exception.getMessage() + "\n\n\n\n\n");
		} finally {
			if (redisConnection != null) {
				redisConnection.close();
			}
		}
		return status;

	}

}
