package com.codecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecompiler.entity.ResponseToFE;

@Controller
public class pythonController {

	@PostMapping(value = "/pythoncompiler")
	@ResponseBody
	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
			throws IOException {
		System.out.println("Welcome to python Controller =>");
		ResponseToFE responsef = new ResponseToFE();
		String total = "";
		Process pro = null;
		BufferedReader in = null;
		String line = null;
		String language = (String) data.get("language");
		System.out.println("language"+language);
		System.out.println("code"+data.get("code"));
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
			System.err.println("Error Done : " + in.readLine());
			while ((line = in.readLine()) != null) {
				System.out.println("line - " + line);
				total += line + "\n";
			}
					
		if(total == "") {
			in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println("line - " + line);
				total += line + "\n";
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End");		
		responsef.setTotalSent(total);
		return ResponseEntity.ok(responsef);
	}
}
