package com.codecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codecompiler.entity.Question;
import com.codecompiler.entity.TestCases;
import com.codecompiler.service.CommonService;
@Controller
public class HomeController {
	
	@Autowired
	private CommonService commonService;	
	
	@RequestMapping("/home")
	public String home() {
		return "compilerIDE";
	}
		
	@PostMapping("/savequestion")
	public ResponseEntity<Question> saveQuestion() throws IOException {
		List<TestCases> testCases = new ArrayList<>();		
		String table = ""; 
		table="2" + "\r\n" + "4"+ "\r\n" + "6"+ "\r\n" + "8"+ "\r\n" + "10"+ "\r\n" + "12"+ "\r\n" + "14"+ "\r\n" + "16"+ "\r\n" + "18"+ "\r\n" + "20";		
		TestCases t1=new TestCases(1,"2",table);
		testCases.add(t1);
		table="3" + "\r\n" + "6"+ "\r\n" + "9"+ "\r\n" + "12"+ "\r\n" + "15"+ "\r\n" + "18"+ "\r\n" + "21"+ "\r\n" + "24"+ "\r\n" + "27"+ "\r\n" + "30";		
		TestCases t2=new TestCases(2,"3",table);
        testCases.add(t2);
       table="4" + "\r\n" + "8"+ "\r\n" + "12"+ "\r\n" + "16"+ "\r\n" + "20"+ "\r\n" + "24"+ "\r\n" + "28"+ "\r\n" + "32"+ "\r\n" + "36"+ "\r\n" + "40";		
		TestCases t3=new TestCases(3,"4",table);
        testCases.add(t3); 
        
        File inputFile = new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+"output.txt");	
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		table = ""; 
		String input = "";
		while((input = inputReader.readLine()) != null){
			table=table+"\r\n"+input;
			}
		table=table.substring(2,table.length());
		TestCases t4=new TestCases(4,"5",table);
        testCases.add(t4); 
		//System.out.println(table);
        
        Question question = new Question(7,"Write a program to print table ?",testCases);
        commonService.saveQuestion(question);
		return ResponseEntity.ok(question);
	}
	
	@GetMapping("/getquestion/{questionId}")
	public ResponseEntity<List<TestCases>> getQuestion(@PathVariable int questionId) {
		List<Question> question = commonService.getQuestionFromDataBase(questionId);
		//System.out.println(question);
		List<TestCases> testCasesCollection = null;
		for (Question q : question) {
			testCasesCollection = q.getTestcases();
		}
		for (TestCases tastCases : testCasesCollection) {			
           System.out.println(tastCases.getOutput());
		}
		return ResponseEntity.ok(testCasesCollection);		
	}
}


	

