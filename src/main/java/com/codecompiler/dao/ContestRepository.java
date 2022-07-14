package com.codecompiler.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.codecompiler.entity.Contest;
import com.codecompiler.entity.Question;

public interface ContestRepository extends  MongoRepository<Contest,String>{

	public Contest findByContestIdAndContestLevel(String contestid, String contestLevel);
	
	//public List<Contest> findByIdAndContestLevel(String id, String contestLevel);	
	
}
