package com.codecompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
		String language = (String) data.get("language");
		System.out.println("language"+language);
		String seperator = File.separator;
		// String uid = RandomStringUtils.randomAlphabetic(10);

		if (language.equals("python")) {
			String uid = "HelloPython";
			// File fl = new File(uid+"."+language);
			FileWriter fl = new FileWriter(
					"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "py");
			PrintWriter pr = new PrintWriter(fl);
			pr.write((String) data.get("code"));
			pr.flush();
			pr.close();
			Process pro = null;
			BufferedReader in = null;
			String line = null;
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
			// File fl = new File(uid+"."+language);
			FileWriter fl = new FileWriter(
					"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "java");
			PrintWriter pr = new PrintWriter(fl);
			pr.write((String) data.get("code"));
			pr.flush();
			pr.close();
			Process pro = null;
			BufferedReader in = null;
			String line = null;
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
			// File fl = new File(uid+"."+language);
			FileWriter fl = new FileWriter(
					"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "c");
			PrintWriter pr = new PrintWriter(fl);
			pr.write((String) data.get("code"));
			pr.flush();
			pr.close();
			Process pro = null;
			BufferedReader in = null;
			String line = null;
			try {
				pro = Runtime.getRuntime().exec("C:\\TDM-GCC-64\\bin\\gcc.exe HelloC.c", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));				
				pro = Runtime.getRuntime().exec("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\a.exe", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
				System.out.println(total);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(language.equals("cpp")) {
			String uid = "HelloCPP";
			// File fl = new File(uid+"."+language);
			FileWriter fl = new FileWriter(
					"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + uid + "." + "cpp");
			PrintWriter pr = new PrintWriter(fl);
			pr.write((String) data.get("code"));
			pr.flush();
			pr.close();
			Process pro = null;
			BufferedReader in = null;
			String line = null;
			try {
				pro = Runtime.getRuntime().exec("C:\\TDM-GCC-64\\bin\\g++.exe HelloCPP.cpp", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
				pro = Runtime.getRuntime().exec("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\a.exe", null,
						new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"));
				in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = in.readLine()) != null) {
					System.out.println("line - " + line);
					total += line + "\n";
				}
				System.out.println(total);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

		responsef.setTotalsent(total);

		return ResponseEntity.ok(responsef);
	}

}
