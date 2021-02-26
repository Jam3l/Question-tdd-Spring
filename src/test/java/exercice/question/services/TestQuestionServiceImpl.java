package exercice.question.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.commons.annotation.Testable;

import exercice.question.dto.NouvelleQuestion;
import exercice.question.dto.ReponseUtilisateur;
import exercice.question.dto.ResultatQuestion;
import exercice.question.entities.Question;
import exercice.question.entities.Reponse;
import exercice.question.exceptions.QuestionNotFoundException;
import exercice.question.repositories.QuestionRepository;
import exercice.question.repositories.fake.FakeQuestionRepository;
import exercice.question.services.impl.QuestionServiceImpl;

@Testable
@TestInstance(Lifecycle.PER_CLASS)
public class TestQuestionServiceImpl {

	private QuestionRepository  repository  = new FakeQuestionRepository();
	private QuestionService questionService = new QuestionServiceImpl(repository);
	private NouvelleQuestion nouvelleQuestion = new NouvelleQuestion();

	/**
	 * Fonction qui s'exécute avant toutes les autres pour chaque test
	 * **/
	@BeforeAll
	public void beforeAll() {
		nouvelleQuestion.setQuestion("Ma question");
		nouvelleQuestion.getReponses().add("Reponse A");
		nouvelleQuestion.getReponses().add("Reponse B");
		nouvelleQuestion.getReponses().add("Reponse C");
		nouvelleQuestion.getIdDesReponseValide().add(0);
	}

	@Test
	@DisplayName("1.1 : Même titre entre Nouvelle question et Question créée")
	public void titreIdentiqueNouvelleQuestionEtQuestionCreee() {
		Question question = questionService.nouvelleQuestion(nouvelleQuestion);
		assertEquals("Ma question", question.getTitre());		
	}

	@Test
	@DisplayName("1.2 : Même nombre nombre de réponses entre Nouvelle question et Question créée")
	public void nbreReponsesIdentiqueNouvelleQuestionEtQuestionCreee() {
		List<String> reponses = new ArrayList<>();
		Question question = questionService.nouvelleQuestion(nouvelleQuestion);
		assertEquals(nouvelleQuestion.getReponses().size(), question.getReponses().size());
	}

	@Test
	@DisplayName("1.3 : Même titre des réponses entre Nouvelle question et question créée")
	public void TitreReponsesIdentiqueNouvelleQuestionEtQuestionCreee() {
		List<String> reponses = new ArrayList<>();
		Question question = questionService.nouvelleQuestion(nouvelleQuestion);
		for(int i=0; i<question.getReponses().size(); i++) {
			assertEquals(nouvelleQuestion.getReponses().get(i), question.getReponses().get(i).getTitre());
		}

	}

	@Test
	@DisplayName("1.4 : ID vrai pour Nouvelle Question qui ont leur ID dans Question")
	public void IdVraiNouvelleQuestionPourIdDansQuestionCreee() {
		List<Integer> listIdValid = new ArrayList<Integer>();
		List<Integer> questionListIdValid = new ArrayList<Integer>();
		listIdValid.add(2);
		nouvelleQuestion.setIdDesReponseValide(listIdValid);
		Question question = questionService.nouvelleQuestion(nouvelleQuestion);		
		for(int i=0; i<question.getReponses().size(); i++) {
			if(question.getReponses().get(i).isValide()) {
				questionListIdValid.add(i);
			}
		}
		assertEquals(nouvelleQuestion.getIdDesReponseValide(), questionListIdValid);
	}

	@Test
	@DisplayName("1.5 : ID faux pour Nouvelle Question qui n'ont pas leur ID dans Question")
	public void IdFauxNouvelleQuestionPourIdDansQuestionCreee() {
		List<Integer> listIdValid = new ArrayList<Integer>();
		listIdValid.add(2);
		nouvelleQuestion.setIdDesReponseValide(listIdValid);

		Question question = questionService.nouvelleQuestion(nouvelleQuestion);			

		int indexReponse = 0;
		for(Reponse reponse : question.getReponses()) {
			boolean estValide = nouvelleQuestion.getIdDesReponseValide().contains(indexReponse);
			assertEquals(estValide, reponse.isValide());
			indexReponse++;
		}
	}

	@Test
	@DisplayName("2.1 : Vérifie Sauvegarde Question dans le Repository.")
	public void SauvegarderQuestionDansRepository() {
		Question question = questionService.sauvegarderQuestion(nouvelleQuestion);		
		Optional<Question> optional = repository.findById(question.getId());		
		assertEquals(true, optional.isPresent());		
		assertEquals(nouvelleQuestion.getQuestion(), question.getTitre());
	}

	@Test
	@DisplayName("2.2 : Vérifie méthode trouverQuestionParId après sauvegarde de la question.")
	public void TestTrouverQuestionParID() {
		Question questionSauvee = questionService.sauvegarderQuestion(nouvelleQuestion);
		Question questionRecuperee = questionService.trouverQuestionParId(questionSauvee.getId());
		assertEquals(questionSauvee, questionRecuperee);
	}


	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("Un utilisateur peut envoyer ses réponses pour avoir un résultat")
	class TestResultatUtilisateur {

		Question question = new Question();
		ReponseUtilisateur reponseUtilisateur = new ReponseUtilisateur();

		@BeforeAll
		public void reponseBeforeAll() {
			Reponse reponse1 = Reponse.builder()
					.titre("Reponse valide")
					.valide(true)
					.build();
			Reponse reponse2 = Reponse.builder()
					.titre("Reponse invalide")
					.valide(false)
					.build();
			question = Question.builder()
					.titre("Titre test")
					.reponses(List.of (reponse1, reponse2))
					.build();
			
			question = repository.save(question);			
			reponseUtilisateur.setQuestionId(question.getId());
			reponseUtilisateur.setReponses(List.of(0));
		}

		@Test
		@DisplayName("3.1 : Vérifie reponse non nulle si ID reponse valide.")
		public void RetourneResultatQuestionNonNulleSiIdValid() {
			ResultatQuestion resultatQuestion = questionService.verificationDesReponses(reponseUtilisateur);
			assertNotNull(resultatQuestion);			
		}

		@Test
		@DisplayName("3.2 : Lève une exception si ID non valide.")
		public void ExceptionSiIdReponseNonValide() {
			ReponseUtilisateur reponseMauvaisID = new ReponseUtilisateur();
			reponseMauvaisID.setQuestionId(-1);
			assertThrows(QuestionNotFoundException.class, () -> {
				questionService.verificationDesReponses(reponseMauvaisID);
			});
		}
		
		@Test
		@DisplayName("3.3 : Si bonnes réponses.")
		public void testBonnesReponses () {
			ResultatQuestion resultat = questionService.verificationDesReponses(reponseUtilisateur);
			assertTrue(resultat.isBonneReponse());
		}
		
		@Test
		@DisplayName("3.4 : Si mauvaises réponses.")
		public void testMauvaisesReponses () {
			ReponseUtilisateur mauvaiseReponse = new ReponseUtilisateur();
			mauvaiseReponse.setQuestionId(question.getId());
			mauvaiseReponse.setReponses(List.of(1));
			ResultatQuestion resultat = questionService.verificationDesReponses(mauvaiseReponse);
			assertFalse(resultat.isBonneReponse());
		}


	}

}
