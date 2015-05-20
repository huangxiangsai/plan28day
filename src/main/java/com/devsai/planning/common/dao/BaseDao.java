package com.devsai.planning.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




/**
 * DAO支持类实现
 * @author ThinkGem
 * @version 2013-05-15
 * @param <E>
 * @param <T>
 */
@Repository
public class BaseDao<E> implements Serializable{

	/**
	 * SessionFactory
	 */
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	@Resource(name="queryTranslatorFactory")
	private QueryTranslatorFactory queryTranslatorFactory;
	

	/**
	 * 获取 Session
	 */
	public Session getSession(){  
	  return sessionFactory.getCurrentSession();
	}

	public E getById(Class cla,Long id) {
		Query query= this.getSession().createQuery("from "+(cla).getName()+" where id="+id);		
		return (E) query.uniqueResult();
		
	}
	
	public int save(Serializable object){
		return (Integer)this.getSession().save(object);
	}
	
	public List<E> find(String hql){
		return this.getSession().createQuery(hql).list();
	}
	
	public E findOne(String hql){
		return (E)this.getSession().createQuery(hql).uniqueResult();
	}
	
	/**
	 * 强制与数据库同步
	 */
	public void flush(){
		getSession().flush();
	}

	/**
	 * 清除缓存数据
	 */
	public void clear(){ 
		getSession().clear();
	}
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public QueryTranslatorFactory getQueryTranslatorFactory() {
		return queryTranslatorFactory;
	}

	public void setQueryTranslatorFactory(
			QueryTranslatorFactory queryTranslatorFactory) {
		this.queryTranslatorFactory = queryTranslatorFactory;
	}
	
	
}