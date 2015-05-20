package com.devsai.planning.exceptions;

public class PlanningException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3287814877737041013L;

	private String code ;
	
	private String msg;
	
	public PlanningException(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}

}
