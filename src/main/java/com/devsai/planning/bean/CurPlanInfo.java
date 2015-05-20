package com.devsai.planning.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 打卡记录表
 * @author huangxiangsai
 *
 */
@Entity(name="CurPlanInfo")
@Table(name="p_curplan_info")
public class CurPlanInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7068890917546520837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="record_id")
	private PlanningRecord record;//用户计划表ID
	
	
	private Date signinDate;  //打卡日期
	
	@Column(length=2000)
	private String mood;  //当天的心情

	//后续有照片与当天记录关联
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlanningRecord getRecord() {
		return record;
	}

	public void setRecord(PlanningRecord record) {
		this.record = record;
	}

	public Date getSigninDate() {
		return signinDate;
	}

	public void setSigninDate(Date signinDate) {
		this.signinDate = signinDate;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

}
