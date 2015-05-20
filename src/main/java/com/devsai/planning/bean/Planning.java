package com.devsai.planning.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 计划分类表
 * @author huangxiangsai
 *
 */
@Entity(name="Planning")
@Table(name="p_planning")
public class Planning implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4600828791160313690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String code; //分类code
	
	@Column
	private String title; //分类名称
	
	@Column
	private String imgUrl; //分类图片

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String url) {
		this.imgUrl = url;
	}

}
