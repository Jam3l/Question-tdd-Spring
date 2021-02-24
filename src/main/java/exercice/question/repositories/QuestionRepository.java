package exercice.question.repositories;

import java.util.Optional;

import exercice.question.entities.Question;

/**
 * Cette interface permet de sauvegarder une question.
 */
public interface QuestionRepository {
	
	/**
	 * Cette methode sauvegarde une nouvelle question.
	 * @param entity question a sauvegarder.
	 * @return la question avec son id.
	 */
	public Question save(Question entity);
	/**
	 * Cette methode retourne un {@link Optional} contenant une question portant l'id passé en parametre.
	 * @param id de la question a rechercher.
	 * @return Optional contenant la question portant l'id.
	 */
	public Optional<Question> findById(long id);

}
