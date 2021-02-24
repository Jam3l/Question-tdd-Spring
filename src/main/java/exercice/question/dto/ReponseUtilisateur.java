package exercice.question.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ReponseUtilisateur {

	private long questionId;
	private List<Integer> reponses = new ArrayList<>();
}
