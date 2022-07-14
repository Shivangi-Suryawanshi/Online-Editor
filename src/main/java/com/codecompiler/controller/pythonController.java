//package com.codecompiler.controller;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
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
//import com.codecompiler.entity.SampleTestCase;
//import com.codecompiler.entity.TestCases;
//import com.codecompiler.service.CommonService;
//
//@Controller
//public class pythonController {
//	
//	@Autowired
//	private CommonService commonService;
//	@Autowired private BinaryDataController binaryDataController;
//	
//	@PostMapping(value = "/pythoncompiler")
//	@ResponseBody
//	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
//			throws IOException {
//		System.out.println("Welcome to python Controller =>");
//		ResponseToFE responsef = new ResponseToFE();
//		String studentId = UUID.randomUUID().toString();
//		String total = "";
//		String output = "";
//		Process pro = null;
//		BufferedReader in = null;
//		String line = null;
//		String language = (String) data.get("language");
//		System.out.println("language"+language);
//		System.out.println("code"+data.get("code"));
//		int questionId = Integer.parseInt((String) data.get("questionId"));
//		String flag = (String) data.get("flag");
//		System.out.println("QuestionId:- "+questionId);
//		
//		String uid = "HelloPython";			
//		FileWriter fl = new FileWriter(
//				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "py");
//		PrintWriter pr = new PrintWriter(fl);
//		pr.write((String) data.get("code"));
//		pr.flush();
//		pr.close();			
//		try {								
//			pro = Runtime.getRuntime().exec("py HelloPython.py", null,
//					new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
//			System.out.println("Compilation done");
//			in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
//			//System.err.println("Error Done : " + in.readLine());
//			while ((line = in.readLine()) != null) {
//				//System.out.println("line - " + line);
//				total += line + "\n";
//			}
//
//			ArrayList<String> testCasesSuccess = new ArrayList<String>();
//					
//		if(total == "") {
//			if (flag.equals("submit")) {
//				System.out.println("submit : ");
//				System.out.println("studentId : "+studentId);
//				binaryDataController.saveFile(studentId,uid + "." + "py");
//				List<TestCases> testCases = commonService.getTestCase(questionId);
//				for(TestCases testCase : testCases) {
//					 String input = testCase.getInput();
//					 System.out.println("input : "+input);
//				pro = Runtime.getRuntime().exec("py HelloPython.py "+input, null,
//						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
//				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//				while ((line = in.readLine()) != null) {
//					System.out.println("line - " + line);
//					 output = line;	
//					 System.out.println("output"+output);
//					if(output.contains(testCase.getOutput())) {
//						//.equals(testCase.getOutput())
//						testCasesSuccess.add("Pass");
//					}else {
//						testCasesSuccess.add("Fail");
//					}
//					total += line + "\n";
//				}			
//				}
//				
//			}else if (flag.equals("test")) {
//				System.out.println("test : ");
//				List<SampleTestCase> sampleTestCase = commonService.getSampleTestCase(questionId);
//				for(SampleTestCase testCase : sampleTestCase) {
//					 String input = testCase.getInput();
//					 System.out.println("input : "+input);
//				pro = Runtime.getRuntime().exec("py HelloPython.py "+input, null,
//						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
//				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//				while ((line = in.readLine()) != null) {
//					System.out.println("line - " + line);
//					 output = line;	
//					 System.out.println("output"+output);
//					if(output.contains(testCase.getOutput())) {
//						//.equals(testCase.getOutput())
//						testCasesSuccess.add("Pass");
//					}else {
//						testCasesSuccess.add("Fail");
//					}
//					total += line + "\n";
//				}
//			
//				}
//			}
//			
//			
//			
//			
//			}
//		System.out.println(testCasesSuccess);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("End");		
//		responsef.setTotalSent(total);
//		return ResponseEntity.ok(responsef);
//	}
//}
