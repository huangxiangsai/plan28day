package com.devsai.planning.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devsai.planning.bean.enums.SexEnum;
/**
 * 用户表
 * @author huangxiangsai
 *
 */
@Entity(name="User")
@Table(name="p_user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1523162835087591224L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String openId;  //用户的唯一标识
	
	@Column
	private String nickname; //用户昵称
	
	@Column
	private SexEnum sex; //性别
	
	@Column
	private String city;  //用户所在城市
	 
	@Column
	private String headimgurl;  //用户头像

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	

}
