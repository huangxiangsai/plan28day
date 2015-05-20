package com.devsai.planning.test;

import javax.annotation.Resource;


import com.devsai.planning.bean.Planning;
import com.devsai.planning.common.dao.BaseDao;

public class TestMain {
	
	@Resource
	private BaseDao baseDao;
	
	
	public void getCategory(){
		Planning category = (Planning)baseDao.getById(Planning.class, 1l);
		String title = category.getTitle();
		System.out.print(title);
	}

}
