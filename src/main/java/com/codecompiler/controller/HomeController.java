package com.codecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
    @RequestMapping("/home")
	public String home() {
		return "compilerIDE";
	}
    
    @PostMapping(value="/compiler")
	public String getCompiler(@RequestBody Map<String,Object> data) throws IOException {
		System.out.println("data:- "+data);
		String language = (String) data.get("language");
		
		System.out.println("language:- "+language);
		System.out.println("code:- \n"+data.get("code"));
		
		String seperator = File.separator;
		System.out.println("seperator:- "+seperator);
		
		//String uid = RandomStringUtils.randomAlphabetic(10);
		String uid = "Main";
		//File fl = new File(uid+"."+language);
		
		FileWriter fl = new FileWriter("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+uid+"."+language);
		PrintWriter pr = new PrintWriter(fl);
		pr.write((String) data.get("code"));
//		fl.write((String) data.get("code"));
		System.out.println("File:- "+pr);
		pr.flush();
		pr.close();
		
		System.out.println("File:- "+fl);
	//C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp	
		Process pro = null;
        BufferedReader in = null;
        String line=null;
		  try {

	            pro = Runtime.getRuntime().exec("javac.exe Main.java",null,new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
	            System.out.println("Compilation done");

	            in = new BufferedReader(
	                    new InputStreamReader(pro.getErrorStream()));
	            System.err.println("Error : "+ in.readLine());
	            pro = Runtime.getRuntime().exec("java.exe Main",null,new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));

	            in = new BufferedReader(
	                    new InputStreamReader(pro.getInputStream()));
	            System.out.println("Execution Done : "+ in.readLine());
	            while ((line = in.readLine()) != null) {
	                System.out.println(" " + line);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return "compilerIDE";
	}
	
}
