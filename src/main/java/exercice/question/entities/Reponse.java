package exercice.question.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe represente les reponses aux questions.
 * Une reponse peut etre valide ou invalide.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reponse {

	private long id;
	private String titre;
	private boolean valide;
}
