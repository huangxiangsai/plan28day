package com.devsai.planning.web.common;

import java.io.Serializable;

public class CommonResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2165061008932831425L;

	private String code;
	
	private String msg;
	
	private Object data;
	
	public CommonResult(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public void add(Object data){
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
