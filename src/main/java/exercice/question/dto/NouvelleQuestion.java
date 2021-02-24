package exercice.question.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Cette classe permet de creer une nouvelle question.
 */
@Data
public class NouvelleQuestion {

	/**
	 * Titre de la question
	 */
	private String question;
	/**
	 * Liste des reponses sous forme de chaine de char.
	 */
	private List<String> reponses = new ArrayList<>();
	/**
	 * Id des reponses valides dans la liste des reponses.
	 */
	private List<Integer> idDesReponseValide = new ArrayList<>();
}
