package exercice.question.repositories.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import exercice.question.entities.Question;

import exercice.question.repositories.QuestionRepository;

@Repository
public class FakeQuestionRepository implements QuestionRepository {

	private List<Question> questions = new ArrayList<>();
	
	@Override
	public Question save(Question entity) {
		entity.setId(questions.size());
		this.questions.add(entity);
		return entity;
	}

	@Override
	public Optional<Question> findById(long id) {
		for (Question question : questions) {
			if (question.getId() == id) return Optional.of(question);
		}
		return Optional.empty();
	}

}
