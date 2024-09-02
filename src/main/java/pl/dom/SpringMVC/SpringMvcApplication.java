package pl.dom.SpringMVC;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.core.net.SyslogOutputStream;
import pl.dom.SpringMVC.model.Content;
import pl.dom.SpringMVC.model.Status;
import pl.dom.SpringMVC.model.Type;
import pl.dom.SpringMVC.repo.ContentRepo;

@SpringBootApplication
public class SpringMvcApplication {

	public static void main(String[] args) {
		 ConfigurableApplicationContext ctx =SpringApplication.run(SpringMvcApplication.class, args);
		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
		
//		RestTemplate rt = (RestTemplate)ctx.getBean("restTemplate");
//		System.out.println(rt);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ContentRepo repo) {
		return a -> {
			System.out.println("asdASDasd");
			
			Content content = new Content(null, 
					"First Post",
					"First Post description",
					Status.IDEA ,
					Type.ARTICLE,
					LocalDateTime.now(),
					null,
					"");
			repo.save(content);
			
			
			repo.save(new Content(null, 
					"First Post",
					"First Post description",
					Status.IDEA ,
					Type.ARTICLE,
					LocalDateTime.now(),
					null,
					""));
			

		};
	}
}
