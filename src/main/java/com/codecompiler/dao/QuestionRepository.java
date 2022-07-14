package com.codecompiler.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codecompiler.entity.Question;



@Repository
public interface QuestionRepository extends MongoRepository<Question,Integer>{

	public List<Question> findByQuestionId(String questionId);
    public ArrayList<Question> findByContestId(String contestId);
	public ArrayList<Question> findByContestIdAndContestLevel(String contestId,String contestLevel);
	public void deleteByQuestionId(String questionId);
	
}

