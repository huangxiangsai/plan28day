package com.devsai.planning.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

/**
 * 用户计划记录表
 * @author huangxiangsai
 *
 */
@Entity(name="PlanningRecord")
@Table(name="p_planning_record")
public class PlanningRecord implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST,optional=false)
	@JoinColumn(name="u_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST,optional=false)
	@JoinColumn(name="p_id")
	private Planning planning;
	
	private Date firstDate;   //第一次选择的日期
	
	private int newestDays ;//最新打卡天数  为28天时 状态为 完成   
	
	private Date newestDate; //最新的打卡日期
	
	private int status = 1; //状态 1 未完成  2 完成
	
	private Date complateDate;  //完成28天计划日期
	
	private String voucher; //代金券  完成后获得
	
	private Date createDate; //创建时间
	
	
	@OneToMany(mappedBy="record",targetEntity=CurPlanInfo.class)
	private List<CurPlanInfo> infoList = Lists.newArrayList();

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Planning getPlanning() {
		return planning;
	}


	public void setPlanning(Planning planning) {
		this.planning = planning;
	}



	public Date getFirstDate() {
		return firstDate;
	}


	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}


	public int getNewestDays() {
		return newestDays;
	}


	public void setNewestDays(int newestDays) {
		this.newestDays = newestDays;
	}


	public Date getNewestDate() {
		return newestDate;
	}


	public void setNewestDate(Date newestDate) {
		this.newestDate = newestDate;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getComplateDate() {
		return complateDate;
	}


	public void setComplateDate(Date complateDate) {
		this.complateDate = complateDate;
	}


	public String getVoucher() {
		return voucher;
	}


	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}


	public List<CurPlanInfo> getInfoList() {
		return infoList;
	}


	public void setInfoList(List<CurPlanInfo> infoList) {
		this.infoList = infoList;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
