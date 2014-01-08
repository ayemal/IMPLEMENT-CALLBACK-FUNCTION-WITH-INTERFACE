package com.example.androidcallback.POJO;

import java.util.ArrayList;


public class XMLCatTagClass {
	public ArrayList<XMLItemTagClass>  itemTagClasses = new ArrayList<XMLItemTagClass>();
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
