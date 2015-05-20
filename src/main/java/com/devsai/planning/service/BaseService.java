package com.devsai.planning.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsai.planning.bean.User;
import com.devsai.planning.common.dao.BaseDao;
import com.devsai.planning.security.SecurityConstans;
import com.devsai.planning.utils.VarPool;
@Service
public class BaseService {
	
	@Resource
	protected BaseDao baseDao;
	
	@Resource
	protected VarPool varPool;
	
	@Transactional
	public <E extends Serializable> E getById(Class clas, Long id){
		return (E)baseDao.getById(clas, id);
	}
	
	
	public User getCurUser(){
		return varPool.get(SecurityConstans.SECURITY_USER);
	}
	

}
