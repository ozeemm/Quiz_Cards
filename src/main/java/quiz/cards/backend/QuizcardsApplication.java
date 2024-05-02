package quiz.cards.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizcardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuizcardsApplication.class, args);
		System.out.println("Server started!");
	}
}
