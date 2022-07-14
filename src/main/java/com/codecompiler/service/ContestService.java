package com.codecompiler.service;

import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecompiler.dao.ContestRepository;
import com.codecompiler.dao.QuestionRepository;
import com.codecompiler.entity.Contest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class ContestService {

	@Autowired
	ContestRepository contestRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	public Contest saveContest(Contest contest) {
		Contest con = contestRepository.save(contest);				
		return con;
	}
	
	public List<Contest> getAllContest() {
		List<Contest> contestList = contestRepository.findAll();	
		return contestList;
	}
	
	public Contest getContestBasedOnLevel(String cId, String clevel) {
		Contest contest = contestRepository.findByContestIdAndContestLevel(cId, clevel);	
		System.out.println("---->"+contest);
		return contest;
	}

	public void deleteContest(String contestId) {
		System.out.println("@@@@@@@@ 2"+contestId);
		
		 contestRepository.delete(contestId);			
			}
	
}
