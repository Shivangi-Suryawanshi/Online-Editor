package com.codecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		List<TestCases> testCases = commonService.getTestCase(questionId);
				
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




//
//#
//#spring.servlet.multipart.enabled=true
//#spring.datasource.username=root
//#spring.datasource.password=root
//#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
//#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
//#spring.jpa.hibernate.ddl-auto=update
//#spring.mvc.view.prefix: /WEB-INF/jsp/
//#spring.mvc.view.suffix: .jsp
//#spring.jpa.properties.hibernate.globally_quoted_identifiers=true