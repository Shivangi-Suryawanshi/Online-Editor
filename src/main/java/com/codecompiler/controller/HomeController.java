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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecompiler.entity.ResponseToFE;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home() {
		return "compilerIDE";
	}

	@PostMapping(value = "/compiler")
	@ResponseBody
	public ResponseEntity<ResponseToFE> getCompiler(@RequestBody Map<String, Object> data, Model model)
			throws IOException {
		ResponseToFE responsef = new ResponseToFE();
		String total = "";
		Process pro = null;
		BufferedReader in = null;
		String line = null;
		String language = (String) data.get("language");
		System.out.println("language"+language);
		System.out.println("code"+data.get("code"));
		// String uid = RandomStringUtils.randomAlphabetic(10);

		if (language.equals("python")) {
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
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
				while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("End");
		} else if (language.equals("java")) {
			String uid = "Main";
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
				System.err.println("Error : " + in.readLine());

				pro = Runtime.getRuntime().exec("java.exe Main", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
				System.out.println(total);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(language.equals("c")) {
			String uid = "HelloC";
			FileWriter fl = new FileWriter(
					"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "c");
			PrintWriter pr = new PrintWriter(fl);
			pr.write((String) data.get("code"));
			pr.flush();
			pr.close();			
			
			try {
				                               //C:\\TDM-GCC-64\\bin\\gcc.exe
				pro = Runtime.getRuntime().exec("gcc.exe HelloC.c -o exeofc", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
				in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));				
			
				while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
						
			if(total == "") {
				pro = Runtime.getRuntime().exec("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+"exeofc.exe", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
					in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
			}
			
				System.out.println(total);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(language.equals("cpp")) {
			String uid = "HelloCPP";
			FileWriter fl = new FileWriter(
					"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "cpp");
			PrintWriter pr = new PrintWriter(fl);
			pr.write((String) data.get("code"));
			pr.flush();
			pr.close();
			
			try {
				//pro = Runtime.getRuntime().exec("C:\\TDM-GCC-64\\bin\\g++.exe HelloCPP.cpp -o exeofCPP", null,
						
				pro = Runtime.getRuntime().exec("g++.exe HelloCPP.cpp -o exeofCPP", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
				in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));				
				while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
				if(total == "") {
				pro = Runtime.getRuntime().exec("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+"exeofCPP.exe", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
				System.out.println(total);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

		responsef.setTotalsent(total);

		return ResponseEntity.ok(responsef);
	}

}
