package com.devsai.planning.dao;

import org.springframework.stereotype.Repository;

import com.devsai.planning.bean.Planning;
import com.devsai.planning.common.dao.BaseDao;

@Repository
public class CategoryDao extends BaseDao{
	private Class bean = Planning.class;
	
	public Planning getById(Long id) {
		return (Planning)this.getById(bean, id);
	}

}
