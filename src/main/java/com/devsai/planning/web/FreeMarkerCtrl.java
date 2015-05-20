package com.devsai.planning.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("")
public class FreeMarkerCtrl {
	
	@RequestMapping("fm/demo")
	public ModelAndView  index(Model model){
		ModelAndView  view = new ModelAndView("demo");
		
		view.addObject("createDate", new Date(1427950889062l));
		System.out.println("demos");
		return view;
	}
	
	@RequestMapping("/rm")
	public String demo(){
		return "dd";
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
//	@ResponseBody
	public Map<String,String> delete(@PathVariable("id") Long id){
		Map<String,String> result = Maps.newConcurrentMap();
		System.out.println("111111");
		result.put("status", "200");
		return result;
	}

}
