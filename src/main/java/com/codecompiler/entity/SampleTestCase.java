package com.codecompiler.entity;

public class SampleTestCase {

	
	private  String constraints;
	private  String input;
	private  String output;
	
	public SampleTestCase() {
		super();
	 }
	
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
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
	@Override
	public String toString() {
		return "SampleTestCase [constraints=" + constraints + ", input=" + input + ", output=" + output + "]";
	}

	
	
}
