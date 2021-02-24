package exercice.question.services;

import exercice.question.dto.NouvelleQuestion;
import exercice.question.dto.ReponseUtilisateur;
import exercice.question.dto.ResultatQuestion;
import exercice.question.entities.Question;

/**
 * Cette interface definie les methodes du service de verification des questions.
 */
public interface QuestionService {
	/**
	 * Cette methode transforme le DTO (Data transfer object) NouvelleQuestion en Question.
	 * @param nouvelleQuestion a transformer. 
	 * @return Question creer a partir de l'objet nouvelleQuestion
	 */
	public Question nouvelleQuestion(NouvelleQuestion nouvelleQuestion);
	/**
	 * Cette methode verifie que les reponses de l'utilisateurs correspondent aux reponses valides de la question.
	 * @param reponsesUtilisateur
	 * @return
	 */
	public ResultatQuestion verificationDesReponses(ReponseUtilisateur reponsesUtilisateur);
	/**
	 * Retourne la question qui correspond a l'id passer en paramettre.
	 * Si la reponse n'est pas trouver, leve une exception.
	 * @param id
	 * @return
	 */
	public Question trouverQuestionParId(long id);
	/**
	 * Sauvegarde une nouvelle question.
	 * @param nouvelleQuestion donner par un utilisateur.
	 * @return Question sauvegarder.
	 */
	public Question sauvegarderQuestion(NouvelleQuestion nouvelleQuestion);
}
