package com.devsai.planning.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

import com.devsai.planning.bean.User;
import com.devsai.planning.security.SecurityConstans;
import com.devsai.planning.service.BaseService;
import com.devsai.planning.service.PlanningMainService;
import com.devsai.planning.utils.VarPool;

public class BaseController {
	
	@Resource
	protected HttpServletRequest request;
	
	@Resource
	protected WxMpService wxMpService ;
	
	@Resource
	private BaseService baseService;
	
	@Resource
	protected PlanningMainService planService;
	
	@Resource
	protected VarPool varPool;
	
	@ModelAttribute("user")
	public User getCurUser(){
		Long id = (Long)request.getSession().getAttribute(SecurityConstans.SECURITY_LOGIN_ID);
		if(id != null){
			User user = this.baseService.getById(User.class, id);
			return user;
		}
		return null;
	}
	
	public void setUser(User user){
		if(user != null && user.getId() > 0){
			request.getSession().setAttribute(SecurityConstans.SECURITY_LOGIN_ID, user.getId());
			request.getSession().setAttribute(SecurityConstans.SECURITY_USER, user);
			varPool.put(SecurityConstans.SECURITY_USER, user);
		}
	}
	
}
