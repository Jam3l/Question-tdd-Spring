package exercice.question.services.impl;

import java.util.ArrayList;
import java.util.List;

import exercice.question.dto.NouvelleQuestion;
import exercice.question.dto.ReponseUtilisateur;
import exercice.question.dto.ResultatQuestion;
import exercice.question.entities.Question;
import exercice.question.entities.Reponse;
import exercice.question.exceptions.QuestionNotFoundException;
import exercice.question.repositories.QuestionRepository;
import exercice.question.services.QuestionService;

public class QuestionServiceImpl implements QuestionService {

	private QuestionRepository repository;

	public QuestionServiceImpl(QuestionRepository repository) {
		this.repository = repository;
	}

	@Override
	public Question nouvelleQuestion(NouvelleQuestion nouvelleQuestion) {
		Question question = new Question();
		question.setTitre(nouvelleQuestion.getQuestion());
		question.setReponses(alimenterReponse(nouvelleQuestion));
		return question;
	}
	
	public List<Reponse> alimenterReponse(NouvelleQuestion nouvelleQuestion){
		
		List<Reponse> reponses = new ArrayList<>();
		for(int i=0; i<nouvelleQuestion.getReponses().size(); i++) {
			Reponse reponseLoc = new Reponse();			
			reponseLoc.setTitre(nouvelleQuestion.getReponses().get(i));
			reponseLoc.setValide(idEstDansListe(nouvelleQuestion, i));
			reponses.add(reponseLoc);			
		}
		return reponses;		
	}
	
	public boolean idEstDansListe(NouvelleQuestion nouvelleQuestion, Integer index) {
		for(int j=0; j<nouvelleQuestion.getIdDesReponseValide().size(); j++) {
			if (index == nouvelleQuestion.getIdDesReponseValide().get(j))
				return true;
		}
		return false;
	}
	

	@Override
	public ResultatQuestion verificationDesReponses(ReponseUtilisateur reponsesUtilisateur) {
		Question question = trouverQuestionParId(reponsesUtilisateur.getQuestionId());
		ResultatQuestion resultat = new ResultatQuestion();		
		List<Integer> idValides = new ArrayList<>();
		
		for(int idQuestion=0; idQuestion<question.getReponses().size(); idQuestion++) {
			if (question.getReponses().get(idQuestion).isValide()) {
				idValides.add(idQuestion);
			}
		}
		//Collections.sort(reponsesUtilisateur.getReponses());

		boolean valide = idValides.equals(reponsesUtilisateur.getReponses());
		resultat.setBonneReponse(valide);
		return resultat; 
	}
	

	@Override
	public Question trouverQuestionParId(long id) {
		return this.repository.findById(id).orElseThrow( () -> {
			return new QuestionNotFoundException(); 
		});
	}

	@Override
	public Question sauvegarderQuestion(NouvelleQuestion nouvelleQuestion) {
		Question question = new Question();
		question = this.nouvelleQuestion(nouvelleQuestion);
		question = this.repository.save(question);
		return question;
	}

}
