package com.devsai.planning.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsai.planning.bean.Planning;
import com.devsai.planning.service.BaseService;
import com.devsai.planning.service.CategoryService;

@Controller
public class TestCtrl {
	
	@Resource
	private CategoryService categoryService;
	
	@RequestMapping(value="cate/{id}")
	@ResponseBody
	public String testCategory(@PathVariable("id") Long id){
		Planning cate = categoryService.getById( id);
		System.out.println(cate.getTitle());
		return "success";
	}

}
