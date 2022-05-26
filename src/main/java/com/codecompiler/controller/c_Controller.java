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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecompiler.entity.Question;
import com.codecompiler.entity.ResponseToFE;
import com.codecompiler.entity.TestCases;
import com.codecompiler.service.CommonService;

@Controller
public class c_Controller {
	@Autowired
	private CommonService commonService;
	
	public List<TestCases> getTestCase(int questionId){
		List<Question>  question= commonService.getQuestion(questionId);	
		List<TestCases> testCasesCollection = null;
		for (Question q : question) {
			testCasesCollection = q.getTestcases();
		}
		return testCasesCollection;
	}


	@PostMapping(value = "/ccompiler")
	@ResponseBody
	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
			throws IOException {
		System.out.println("Welcome to c Controller =>");
		ResponseToFE responsef = new ResponseToFE();
		String total = "";
		String output = "";
		Process pro = null;
		BufferedReader in = null;
		String line = null;
		String language = (String) data.get("language");
		System.out.println("language"+language);
		System.out.println("code"+data.get("code"));
		int questionId = (int)data.get("questionId");
		System.out.println("QuestionId:- "+questionId);
		List<TestCases> testCases = getTestCase(questionId);
		
		// String uid = RandomStringUtils.randomAlphabetic(10);
		String uid = "HelloC";
		FileWriter fl = new FileWriter(
				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "c");
		PrintWriter pr = new PrintWriter(fl);
		pr.write((String) data.get("code"));
		pr.flush();
		pr.close();						
		try {				                               //C:\\TDM-GCC-64\\bin\\gcc.exe
			pro = Runtime.getRuntime().exec("gcc.exe HelloC.c -o exeofc", null,
					//pro = Runtime.getRuntime().exec("gcc.exe HelloC.c", null,
					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
			in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));				
		
			while ((line = in.readLine()) != null) {
				System.out.println("line - " + line);					
				total += line + "\n";
			}	
			ArrayList<String> testCasesSuccess = new ArrayList<String>();
		if(total == "") {			
				for(TestCases testCase : testCases) {
					 String input = testCase.getInput();
					 System.out.println("Input:- "+input);	
					
			pro = Runtime.getRuntime().exec("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\exeofc.exe "+input, null,
					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
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
			System.out.println(testCasesSuccess);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		responsef.setTotalSent(total);
		return ResponseEntity.ok(responsef);
	}
}
