package com.devsai.planning.web;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsai.planning.bean.User;
import com.devsai.planning.exceptions.PlanningException;
import com.devsai.planning.web.common.CommonResult;

@Controller
public class PlanningMainCtrl extends BaseController{
	
	
	
	/**
	 * 链接入口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/planning")
	public String planning(HttpServletRequest request,Model model){ 
		//存在当前用户的 ，查看用户的计划后 直接返回
		User user = this.getCurUser();
		if(user != null){
			boolean hasPlan = this.planService.hasPlan(user);
			if(hasPlan){
				model.addAttribute("record", this.planService.curPlan(user));
				return "planDo";  //打卡页面
			}else{
				model.addAttribute("list", this.planService.getPlannList());
				return "selectorPlan"; //选择习惯页面
			}
		}
		
		model.addAttribute("list", this.planService.getPlannList());
		String code= request.getParameter("code");
		if(code == null){//auto认证没通过的  获得不到 code
			return "selectorPlan";
		}
		try {
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
			User u = this.planService.checkUser(wxMpUser.getOpenId());
			if(u == null){//用户不存在 创建用户记录
				planService.saveUser(wxMpUser);
			}{	//用户存在 放入session
				this.setUser(u);
			}
			return "selectorPlan";
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "selectorPlan";
	}
	
	
	/**
	 * 保存打卡记录
	 * @param recordId
	 * @return
	 */
	@RequestMapping(value="punchCard/{recordId}")
	@ResponseBody
	public CommonResult punchCard(@PathVariable("recordId") Long recordId){
		try{
			planService.savePunchCard(recordId);
			return new CommonResult("200", "");
		}catch(Exception e){
			String msg = "";
			if(e instanceof PlanningException){
				msg = e.getMessage();
			}
			return new CommonResult("500", msg);
		}
	}
	
	
	
	
	
	/**
	 * 微信验证
	 * @param signature
	 * @param nonce
	 * @param timestamp
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value="checkSignature",method=RequestMethod.GET)
	public @ResponseBody String checkSignature(@RequestParam("signature") String signature
			,@RequestParam("nonce") String nonce
			,@RequestParam("timestamp") String timestamp
			,@RequestParam("echostr") String echostr){
		
		if(!wxMpService.checkSignature(timestamp, nonce, signature)){
			// 消息不合法
			return "none";
		}
		// 消息合法		
		return echostr;		
	}
	
}
