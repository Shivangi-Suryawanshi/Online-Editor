package com.codecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecompiler.entity.Question;
import com.codecompiler.entity.ResponseToFE;
import com.codecompiler.entity.TestCases;
import com.codecompiler.service.CommonService;

@Controller
public class javaController {

	@Autowired
	private CommonService commonService;

	@PostMapping("/savequestion")
	public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
		commonService.saveQuestion(question);
		return ResponseEntity.ok(question);
	}

	@GetMapping("/getquestion/{questionId}")
	public ResponseEntity<List<TestCases>> getQuestion(@PathVariable int questionId) {
		System.out.println("questionId :" + questionId);
		List<Question> question = commonService.getQuestion(questionId);
		System.out.println(question);
		List<TestCases> testCasesCollection = null;
		for (Question q : question) {
			testCasesCollection = q.getTestcases();
		}
		for (TestCases tastCases : testCasesCollection) {
           System.out.println("tastCase : "+tastCases.getId()+" "+tastCases.getInput()+" "+tastCases.getOutput());
		}
		return ResponseEntity.ok(testCasesCollection);
		
	}
	
	public List<TestCases> getTestCase(int questionId){
		List<Question>  question= commonService.getQuestion(questionId);	
		List<TestCases> testCasesCollection = null;
		for (Question q : question) {
			testCasesCollection = q.getTestcases();
		}
		return testCasesCollection;
	}

	@PostMapping(value = "/javacompiler")
	@ResponseBody
	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
			throws IOException {
		System.out.println("Welcome to java Controller =>");
		ResponseToFE responsef = new ResponseToFE();
		String total = "";
		Process pro = null;
		BufferedReader in = null;
		String line = null;
		String language = (String) data.get("language");
		System.out.println("language" + language);
		System.out.println("code" + data.get("code"));
		String uid = "Main";
		String output = "";
		int questionId = (int) data.get("questionId");		
		System.out.println("QuestionId:- " + questionId);
		List<TestCases> testCases = getTestCase(questionId);
		
		
		FileWriter fl = new FileWriter(
				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "java");
		PrintWriter pr = new PrintWriter(fl);
		pr.write((String) data.get("code"));
		pr.flush();
		pr.close();
		try {
			pro = Runtime.getRuntime().exec("javac.exe Main.java", null,
					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
			System.out.println("Compilation done");
			in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
			// System.err.println("Error : " + in.readLine());
			while ((line = in.readLine()) != null) {
				System.out.println("line - " + line);
				total += line + "\n";
			}
			ArrayList<String> testCasesSuccess = new ArrayList<String>();
			if (total == "") {
				for(TestCases testCase : testCases){
			   String input = testCase.getInput();
				System.out.println("Input:-" + input);
				pro = Runtime.getRuntime().exec("java.exe Main " + input, null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
				while ((line = in.readLine()) != null) {					
					output = line;
					System.out.println("expected output:"+testCase.getOutput());
					System.out.println("output:"+output);					
					if(output.contains(testCase.getOutput())) {
						//.equals(testCase.getOutput())
						testCasesSuccess.add("Pass");
					}else {
						testCasesSuccess.add("Fail");
					}
					total += line + "\n";
				}
				}
			}			
			System.out.println("testCasesSuccess = "+testCasesSuccess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		responsef.setTotalSent(total);
		return ResponseEntity.ok(responsef);
	}

}

//@Autowired private JavaService javaService;
//
//@Autowired private CommonService commonService;
//	
//	@GetMapping("/add_questions")
//	public ResponseEntity<Question> addQuestion() {
//		Question question = new Question();
//		question.setQuestionId(1);
//		question.setQuestion("write a program of palindrom number");
//		List<TestCases> test = Arrays.asList(new TestCases(question,"151","151"),new TestCases(question,"121","121"), new TestCases(question,"1221","1221"), new TestCases(question,"876","678"));
//		question.setTestCases(test);
//		Question q = javaService.addQuestion(question);
//		return ResponseEntity.ok(q);
//	}
//	
//	@GetMapping("/get_question") 
//	public ResponseEntity<Question> getQuestion(@RequestBody Question question){
//		System.out.println(question.getQuestionId());
//		int qId = question.getQuestionId();
//		Question q = javaService.getQuestion(qId);
//		return ResponseEntity.ok(q);
//	}
//	
//	public List<TestCases> getTestCase(int questionId){
//		Question q = javaService.getQuestion(questionId);
//		List<TestCases> testCase = q.getTestCases();
//		return testCase;
//	}
//	@PostMapping(value = "/javacompiler")
//	@ResponseBody
//	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
//			throws IOException {
//		System.out.println("Welcome to java Controller =>");
//		
//		ResponseToFE responsef = new ResponseToFE();
//		String total = "";
//		Process pro = null;
//		BufferedReader in = null;
//		String line = null;
//		String language = (String) data.get("language");
//		System.out.println("language"+language);
//		System.out.println("code"+data.get("code"));
//		String uid = "Main";
//		String output = "";
//		int questionId = (int)data.get("questionId");
//		System.out.println("QuestionId:- "+questionId);
//		
//		FileWriter fl = new FileWriter(
//				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "java");
//		PrintWriter pr = new PrintWriter(fl);
//		pr.write((String) data.get("code"));
//		pr.flush();
//		pr.close();			
//		try {
//			pro = Runtime.getRuntime().exec("javac.exe Main.java", null,
//					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
//			System.out.println("Compilation done");
//			in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
//			//System.err.println("Error : " + in.readLine());
//			while ((line = in.readLine()) != null) {
//				System.out.println("line - " + line);
//				total += line + "\n";
//			}
//			//List<TestCases> testCase = getTestCase(questionId);
//			
//		if(total == "") {	
////			for(TestCases test:testCase) {
////				int input = Integer.parseInt(test.getInput());
////				System.out.println("Input:- "+input);	
////				
////			pro = Runtime.getRuntime().exec("java.exe Main "+input, null,
////					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
////			in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
////		while ((line = in.readLine()) != null) {
////				System.out.println("line - " + line);
////				output = line;
////				total += line + "\n";
////			}
////			}
//			
//			File inputFile = new File(CommonProperty.programFileDestination+"input.txt");
//			File outputFile= new File(CommonProperty.programFileDestination+"output.txt");
//			BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
//			BufferedReader outputReader= new BufferedReader(new FileReader(outputFile));
//
//			String input;
//			String testOutput;
//			List<String> testCases = new ArrayList<String>();
//			List<String> userOutput = new ArrayList<String>();
//			
//
//			while((testOutput = outputReader.readLine()) !=null) {
//				testCases.add(testOutput);
//			}
//			while((input = inputReader.readLine()) != null){
//				pro = commonService.extracted("java.exe Main "+input);
//				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//				while ((line = in.readLine()) != null) {
//					
//					userOutput.add(line.trim());
//				}
//			}
//			System.out.println("testCases : "+testCases);
//			System.out.println("userOutput : "+userOutput);
//			if(testCases.containsAll(userOutput)) {
//				System.out.println("Success");
//				total = userOutput+ "\n";
//			} else {
//				System.out.println("Failure");
//				total = userOutput+ "\n";
//			}
//			System.out.println(total);
//			
//			
//			
//		}
//		System.out.println("output : "+total);
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//		responsef.setTotalSent(total);
//		return ResponseEntity.ok(responsef);
//	}
