package jo.seongju.books.core.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "jo.seongju.books.core")
public class BooksCoreImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksCoreImplApplication.class, args);
	}
}
