package exercice.question.services.impl;

import exercice.question.dto.NouvelleQuestion;
import exercice.question.dto.ReponseUtilisateur;
import exercice.question.dto.ResultatQuestion;
import exercice.question.entities.Question;
import exercice.question.repositories.QuestionRepository;
import exercice.question.services.QuestionService;

public class QuestionServiceImpl implements QuestionService {

	private QuestionRepository repository;
	
	public QuestionServiceImpl(QuestionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Question nouvelleQuestion(NouvelleQuestion nouvelleQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultatQuestion verificationDesReponses(ReponseUtilisateur reponsesUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question trouverQuestionParId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question sauvegarderQuestion(NouvelleQuestion nouvelleQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

}
