package com.codecompiler.entity;
import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class TestCases {

	@Id 
	private int id;
	private  String input;	
	private String output;
	public TestCases(int id, String input, String output) {
		super();
		this.id = id;
		this.input = input;
		this.output = output;
	}
	@Override
	public String toString() {
		return "TestCases [id=" + id + ", input=" + input + ", output=" + output + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	

}
