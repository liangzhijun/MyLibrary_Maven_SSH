package org.library.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service("dao")
public class DaoImpl implements Dao
{
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
 	/**
	 * 把Object类存储到数据库
	 * @param object
	 */
	public void save(Object object)
	{
		Session s = sessionFactory.getCurrentSession();
		
		s.save(object);
	}
	
	public void delete(Object ob)
	{
		Session s = sessionFactory.getCurrentSession();
		
		s.delete(ob);
	}
	
	public void delete(String id, Object object)
	{
		Session s = sessionFactory.getCurrentSession();
		
		s.delete(id, object);
	}
	
	public void update(Object ob)
	{
		Session s = sessionFactory.getCurrentSession();
		
		s.update(ob);
	}
	
	public  Object get(Class clazz, String id)
	{
		Session s = sessionFactory.getCurrentSession();
		
		Object ob = s.get(clazz, id);
		
		return ob;
	}
	
	public Query createQuery(String ql)
	{
		Session s = sessionFactory.getCurrentSession();
		
		Query q = s.createQuery(ql);
		
		return q; 
	}
	
	public Query createSQLQuery(Class c, String ql)
	{
		Session s = sessionFactory.getCurrentSession();
		Query query = s.createSQLQuery(ql).addEntity(c);
		
		return query; 
	}
}
