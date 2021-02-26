package exercice.question.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exercice.question.repositories.QuestionRepository;
import exercice.question.services.QuestionService;
import exercice.question.services.impl.QuestionServiceImpl;

@Configuration
public class QuestionConfiguration {
	
	@Bean
	public QuestionService questionService(QuestionRepository repository) {
		return new QuestionServiceImpl(repository);
		
	}
}
