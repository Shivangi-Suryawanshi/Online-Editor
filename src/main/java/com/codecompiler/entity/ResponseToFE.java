package com.codecompiler.entity;

public class ResponseToFE {
private String totalSent;

public String getTotalSent() {
	return totalSent;
}

public void setTotalSent(String totalSent) {
	this.totalSent = totalSent;
}

@Override
public String toString() {
	return "ResponseToFE [totalSent=" + totalSent + "]";
}

public ResponseToFE() {
	super();
	// TODO Auto-generated constructor stub
}

}
