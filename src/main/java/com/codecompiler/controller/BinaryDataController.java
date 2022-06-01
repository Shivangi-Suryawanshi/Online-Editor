package com.codecompiler.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@RestController
public class BinaryDataController {

	@Autowired
	private GridFsOperations gridFsOperations;

	String fileId = "";

	@GetMapping("/saveFiles")
	public String saveFile() throws FileNotFoundException {
		// define metadata
		DBObject metaData = new BasicDBObject();
		metaData.put("organization", "Java Techie");
		metaData.put("type", "data");
		fileId = gridFsOperations.store(new FileInputStream("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\output.txt"), "output.txt","text/plain", metaData).getId().toString();
		System.out.println("File id stored : " + fileId);
		return "File stored successfully...";
	}

	@GetMapping("/retrive/text")
	public String retriveTextFile() throws IOException {
		GridFSDBFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
		dbFile.writeTo("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\static\\participators\\myData.txt");
		System.out.println("File name : " + dbFile.getFilename());
		return "Text File retrived with name : " + dbFile.getFilename();
	}


	@DeleteMapping("/delete/text")
	public String deleteTextFile() throws IOException {
		File f= new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\static\\participators\\myData.txt");
		f.delete();
		return "Text File Deleted";
	}
	
}
