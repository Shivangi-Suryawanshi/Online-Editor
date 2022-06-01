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
public class pythonController {
	
	@Autowired
	private CommonService commonService;

	@PostMapping(value = "/pythoncompiler")
	@ResponseBody
	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
			throws IOException {
		System.out.println("Welcome to python Controller =>");
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
		List<TestCases> testCases = commonService.getTestCase(questionId);
		
		String uid = "HelloPython";			
		FileWriter fl = new FileWriter(
				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "py");
		PrintWriter pr = new PrintWriter(fl);
		pr.write((String) data.get("code"));
		pr.flush();
		pr.close();			
		try {								
			pro = Runtime.getRuntime().exec("py HelloPython.py", null,
					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
			System.out.println("Compilation done");
			in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
			//System.err.println("Error Done : " + in.readLine());
			while ((line = in.readLine()) != null) {
				//System.out.println("line - " + line);
				total += line + "\n";
			}

			ArrayList<String> testCasesSuccess = new ArrayList<String>();
					
		if(total == "") {
			for(TestCases testCase : testCases) {
				 String input = testCase.getInput();
				 System.out.println("input : "+input);
			pro = Runtime.getRuntime().exec("py HelloPython.py "+input, null,
					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
			in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println("line - " + line);
				 output = line;	
				 System.out.println("output"+output);
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
		System.out.println("End");		
		responsef.setTotalSent(total);
		return ResponseEntity.ok(responsef);
	}
}
