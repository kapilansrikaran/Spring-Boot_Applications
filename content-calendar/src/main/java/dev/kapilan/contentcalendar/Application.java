package dev.kapilan.contentcalendar;

import dev.kapilan.contentcalendar.model.Content;
import dev.kapilan.contentcalendar.model.Status;
import dev.kapilan.contentcalendar.model.Type;
import dev.kapilan.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// another method for DataLoad -> functional interface - target for lambda
	@Bean
	CommandLineRunner commandLineRunner(ContentRepository contentRepository){
		return args -> {
			// insert some data into the database
			Content content = new Content(
					null,
					"Hello Chat GPT",
					"All about Chat GPT",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					""
			);
			contentRepository.save(content);

		};
	}



}