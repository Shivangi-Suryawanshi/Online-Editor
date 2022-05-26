//package com.codecompiler.controller;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.codecompiler.entity.Question;
//import com.codecompiler.entity.ResponseToFE;
//import com.codecompiler.entity.TestCases;
//import com.codecompiler.service.JavaService;
//
//@Controller
//public class cpp_Controller {
//	
//@Autowired private JavaService javaService;
//
//public List<TestCases> getTestCase(int questionId){
//	Question q = javaService.getQuestion(questionId);
//	List<TestCases> testCase = q.getTestCases();
//	return testCase;
//}
//	
//	@PostMapping(value = "/cppcompiler")
//	@ResponseBody
//	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
//			throws IOException {
//		System.out.println("Welcome to cpp Controller =>");
//		ResponseToFE responsef = new ResponseToFE();
//		String total = "";
//		Process pro = null;
//		BufferedReader in = null;
//		String line = null;
//		String language = (String) data.get("language");
//		System.out.println("language : "+language);
//		System.out.println("code : "+data.get("code"));
//		int questionId = (int)data.get("questionId");
//		System.out.println("QuestionId:- "+questionId);
//		
//		String uid = "HelloCPP";
//		FileWriter fl = new FileWriter(
//				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "cpp");
//		PrintWriter pr = new PrintWriter(fl);
//		pr.write((String) data.get("code"));
//		pr.flush();
//		pr.close();		
//		try {
//			//pro = Runtime.getRuntime().exec("C:\\TDM-GCC-64\\bin\\g++.exe HelloCPP.cpp -o exeofCPP", null,					
//			pro = Runtime.getRuntime().exec("g++.exe HelloCPP.cpp -o exeofCPP", null,
//					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
//			in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));				
//			while ((line = in.readLine()) != null) {
//				System.out.println("line - " + line);
//				total += line + "\n";
//			}
//			List<TestCases> testCase = getTestCase(questionId);
//			
//			if(total == "") {
//				for(TestCases test:testCase) {
//					int input = Integer.parseInt(test.getInput());
//					System.out.println("Input:- "+input);	
//			pro = Runtime.getRuntime().exec("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+"exeofCPP.exe "+input, null,
//					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
//			in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//		while ((line = in.readLine()) != null) {
//				System.out.println("line - " + line);
//				total += line + "\n";
//			}
//			System.out.println(total);
//			}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
//		responsef.setTotalSent(total);
//		return ResponseEntity.ok(responsef);
//	}
//}
