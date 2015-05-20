package com.devsai.planning.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsai.planning.bean.Planning;

@Service
public class CategoryService extends BaseService{

	@Transactional
	public Planning getById(Long id){
		
		Planning category = new Planning();
		Session session= this.baseDao.getSession();
		session.beginTransaction();
		List  list = session.createSQLQuery("select id,code,title from category where id="+id).list();
		for(int i = 0 ; i < list.size() ; i++){
			
		}
		return (Planning)session.createQuery(" from com.devsai.planning.bean.Category where id="+id).uniqueResult();
	}
	
}
