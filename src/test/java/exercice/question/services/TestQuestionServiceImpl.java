package exercice.question.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import exercice.question.repositories.QuestionRepository;
import exercice.question.repositories.fake.FakeQuestionRepository;
import exercice.question.services.impl.QuestionServiceImpl;

@Testable
public class TestQuestionServiceImpl {

	private QuestionRepository  repository  = new FakeQuestionRepository();
	private QuestionService questionService = new QuestionServiceImpl(repository);
	
	/**
	 * Ecrire vos tests ici.
	 */
	/*
	 * Exemple de test.
	 * @DisplayName permet de donner un nom correct a votre test. C'est le nom afficher lors du resultat des test.
	 * Utiliser des noms qui font comprendre ce que votre test fait.
	@Test
	@DisplayName("Nom de votre test")
	public void nom_du_test() {
		Object resultat = questionService.methodeATester(param);
		assertEquest(expected, resultat);
	}*/
}
