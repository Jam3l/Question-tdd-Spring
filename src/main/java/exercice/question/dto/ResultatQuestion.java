package exercice.question.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResultatQuestion {

	private long questionId;
	private boolean bonneReponse;
	private List<Integer> reponseValideId = new ArrayList<>();
	private List<Integer> reponseInvalideId = new ArrayList<>();
}
