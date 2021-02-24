package exercice.question.entities;

import lombok.Data;

/**
 * Cette classe represente les reponses aux questions.
 * Une reponse peut etre valide ou invalide.
 */
@Data
public class Reponse {

	private long id;
	private String tritre;
	private boolean valide;
}
