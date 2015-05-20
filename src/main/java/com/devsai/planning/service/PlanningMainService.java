package com.devsai.planning.service;

import java.util.Date;
import java.util.List;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsai.planning.bean.CurPlanInfo;
import com.devsai.planning.bean.Planning;
import com.devsai.planning.bean.PlanningRecord;
import com.devsai.planning.bean.User;
import com.devsai.planning.bean.enums.SexEnum;
import com.devsai.planning.exceptions.PlanningException;


@Service
@Transactional(readOnly=true)
@SuppressWarnings("unchecked")
public class PlanningMainService extends BaseService{

	/**
	 * 保存用户信息
	 * @param wxMpUser
	 * @return
	 */
	@Transactional(readOnly=false)
	public int saveUser(WxMpUser wxMpUser){
		User user = new User();
		user.setOpenId(wxMpUser.getOpenId());
		user.setNickname(wxMpUser.getNickname());
		String sex = wxMpUser.getSex();
		SexEnum sexEnum = SexEnum.valueOf(sex);
		user.setSex(sexEnum);
		user.setHeadimgurl(wxMpUser.getHeadImgUrl());
		user.setCity(wxMpUser.getCity());
		
		return this.baseDao.save(user);
	}
	
	/**
	 * 检查用户是否存在
	 * @param openId 
	 * @return
	 */
	public User checkUser(String openId){
		StringBuffer hql = new StringBuffer("from User where 1=1 ");
		if(openId != null ){
			hql.append(" and openId = "+openId+" ");
		}
		User user = (User)this.baseDao.findOne(hql.toString());
		
		return user;
	}
	
	
	/**
	 * 查看用户当前是否有计划
	 * @return
	 */
	public boolean hasPlan(User user){
		PlanningRecord record = this.curPlan(user);
		if(record == null){//表示当前没有计划
			return false;
		}
		return true;
	}
	
	/**
	 * 当前用户的计划
	 * @param user
	 * @return
	 */
	public PlanningRecord curPlan(User user){
		StringBuffer hql = new StringBuffer("from PlanningRecord where 1=1 ");
		if(user.getId() != null){
			hql.append(" and user.id = "+user.getId()+" ");
		}
		hql.append(" and status=1 ");
		
		PlanningRecord record = (PlanningRecord)this.baseDao.findOne(hql.toString());
		return record;
	}
	
	/**
	 * 通过ID 获得用户习惯计划记录
	 * @param id
	 * @return
	 */
	public PlanningRecord getById(Long id){
		return (PlanningRecord)this.baseDao.getById(PlanningRecord.class, id);
	}
	
	/**
	 * 选择计划后 保存计划的记录
	 * @param planning
	 */
	@Transactional(readOnly=false,rollbackFor=PlanningException.class)
	public void savePlanning(Planning planning) throws PlanningException{
		try{
			User user = this.getCurUser();
			PlanningRecord record= new PlanningRecord();
			record.setPlanning(planning);
			record.setUser(user);
			record.setCreateDate(new Date());
			this.baseDao.save(record);
		}catch(Exception e){
			throw new  PlanningException("1001","保存失败");
		}
	}
	
	/**
	 * 保存打卡记录
	 * @param record
	 */
	@Transactional(readOnly=false)
	public void savePunchCard(Long recordId){
		User user = this.getCurUser();
		PlanningRecord record = this.getById(recordId);
		if(record != null) return;
		if( record.getFirstDate() == null){
			record.setFirstDate(new Date());
			record.setNewestDays(1);
		}else{
			int days = record.getNewestDays()+1;
			if(days ==  28){
				record.setComplateDate(new Date());
				record.setStatus(2);
				record.setVoucher("009233"); //代金券
			}
			record.setNewestDate(new Date()); //最新打卡日期			
		}
		this.baseDao.save(record);
		
		CurPlanInfo info = new CurPlanInfo();
		info.setRecord(record);
		info.setSigninDate(new Date());
		this.baseDao.save(info);
	}
	
	/**
	 * 获得所有的习惯列表
	 * @return
	 */
	public List<Planning> getPlannList(){
		return this.baseDao.find("from Planning ");
	}
	
}
