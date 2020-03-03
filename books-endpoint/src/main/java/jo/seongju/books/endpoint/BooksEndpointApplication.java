package jo.seongju.books.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BooksEndpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksEndpointApplication.class, args);
	}

}
