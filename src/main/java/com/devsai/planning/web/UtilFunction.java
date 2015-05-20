package com.devsai.planning.web;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public abstract class UtilFunction implements TemplateMethodModelEx {

}
@Component("dateFunction")
class DateFunction extends UtilFunction{
	public Object exec(List arguments) throws TemplateModelException {
		try{
			Object arg=arguments.get(0);
			TemplateDateModel dateModel = (TemplateDateModel)arguments.get(0);
			Date date= dateModel.getAsDate();
			
			Date now = new Date();
			long time = now.getTime()-date.getTime();
			System.out.println(time/60000);
			String result = (time/(60*1000))+"分钟前";
//			new BooleanModel(false, BeansWrapper.getDefaultInstance())
//			BeansWrapper wrapper = new BeansWrapper();
			return result;
		}catch(Exception e){
			throw new TemplateModelException(e);
		}
	}
}


@Component("curUserFunction")
class CurUserFunction extends UtilFunction{
	public Object exec(List arguments) throws TemplateModelException {
		try{
			User user = new User("sai","27","shanghai");
//			new BooleanModel(false, BeansWrapper.getDefaultInstance())
//			BeansWrapper wrapper = new BeansWrapper();
			return user;
		}catch(Exception e){
			throw new TemplateModelException(e);
		}
	}
}