package com.devsai.planning.bean.enums;

public enum SexEnum {
	
	Man("1"),WOMEN("2");
	
	private String label;
	
	
	private  SexEnum(String label){
		this.label = label;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getName(){
		return this.name();
	}

}
