package Medical.clinic.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableWebMvc
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"Medical.clinic.Model"})
@ComponentScan(basePackages = {"Medical.clinic.Controller","Medical.clinic.Service","Medical.clinic.Repo"})
@EnableJpaRepositories(basePackages = { "Medical.clinic.Repo" })
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}


}
