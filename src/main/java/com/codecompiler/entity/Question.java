package com.codecompiler.entity;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="QuestionAndTestCases")
public class Question {

	@Id
	private int questionId;
	private String question;
	private List<TestCases> testcases;
	public Question(int questionId, String question, List<TestCases> testcases) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.testcases = testcases;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<TestCases> getTestcases() {
		return testcases;
	}
	public void setTestcases(List<TestCases> testcases) {
		this.testcases = testcases;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", question=" + question + ", testcases=" + testcases + "]";
	}
	
	
	
}
