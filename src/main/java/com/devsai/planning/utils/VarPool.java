package com.devsai.planning.utils;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Stack;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 线程级别对象池
 * 
 * @version 1.0 
 * @author Hector.Tong &copy; Ustudy 游学网络
 * <b>Create Time:</b>2014-5-4 上午11:34:44
 */
@Component("varPool")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class VarPool {
	
	private ThreadLocal<Map<String, Object>> pools=new ThreadLocal<>();
	
	private ThreadLocal<Stack<Object>> stacks=new ThreadLocal<>();
	
	private ThreadLocal<Queue<Object>> queue=new ThreadLocal<>();
	
	@Resource(name="systemVar")
	private Properties properties;
	
	public String getProperty(String name){
		return properties.getProperty(name);
	}
	
	public <E>E get(String name){
		if(pools.get()==null){
			pools.set(new HashMap<String, Object>());
			return null;
		}
		return (E) pools.get().get(name);
	}
	
	public void put(String name,Object value){
		if(pools.get()==null){
			pools.set(new HashMap<String, Object>());
		}
		pools.get().put(name, value);
	}
	
	public <E> E pop(){
		if(stacks.get()==null){
			stacks.set(new Stack<>());
			return null;
		}
		return (E) stacks.get().pop();
	}
	
	
	public void push(Object obj){
		if(stacks.get()==null){
			stacks.set(new Stack<>());
		}
	}
	
	public <E> E speek(){
		if(stacks.get()==null){
			stacks.set(new Stack<>());
			return null;
		}
		return (E) stacks.get().peek();
	}
	
	public <E> E poll(){
		if(queue.get()==null){
			queue.set(new LinkedList<>());
			return null;
		}
		return (E) queue.get().poll();
	}
	
	public <E> E qpeek(){
		if(queue.get()==null){
			queue.set(new LinkedList<>());
			return null;
		}
		return (E) queue.get().peek();
	}
	
	public void offer(Object e){
		if(queue.get()==null){
			queue.set(new LinkedList<>());
		}
		queue.get().offer(e);
	}
	
	public void clear(){
		pools.remove();
		stacks.remove();
		queue.remove();
	}
}
