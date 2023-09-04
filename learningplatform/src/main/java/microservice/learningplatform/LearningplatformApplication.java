package microservice.learningplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LearningplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningplatformApplication.class, args);
		System.out.println("hi");
	}

}
