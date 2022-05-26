package com.codecompiler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecompiler.dao.QuestionRepository;
import com.codecompiler.entity.Question;

@Service
public class CommonService {

	@Autowired private QuestionRepository questionRepository;
	
	public Question saveQuestion(Question question) {		
		return questionRepository.save(question);
	}
	
	
	public List<Question> getQuestion(int questionId) {
		System.out.print("Comman service");
		List<Question> question= (List<Question>) questionRepository.findByQuestionId(questionId);
		return question;
	}

}

