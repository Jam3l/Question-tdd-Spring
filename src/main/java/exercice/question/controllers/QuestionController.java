package exercice.question.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import exercice.question.dto.NouvelleQuestion;
import exercice.question.dto.ReponseUtilisateur;
import exercice.question.dto.ResultatQuestion;
import exercice.question.entities.Question;
import exercice.question.exceptions.QuestionNotFoundException;
import exercice.question.services.QuestionService;

@RestController
@CrossOrigin
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	private QuestionService service;
	
	@GetMapping("{id}")
	public Question findById(@PathVariable int id) {
		try {
			return this.service.trouverQuestionParId(id);	
		} catch (QuestionNotFoundException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@PostMapping("")
	public Question save(@RequestBody NouvelleQuestion nouvelleQuestion) {
		return this.service.sauvegarderQuestion(nouvelleQuestion);
	}
	
	@PostMapping("check")
	public ResultatQuestion check(@RequestBody ReponseUtilisateur reponse) {
		return this.service.verificationDesReponses(reponse);
	}
}
