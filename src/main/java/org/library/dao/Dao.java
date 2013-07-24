package org.library.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Dao
{
	private static SessionFactory sessionFactory;
	
	static{
		try
		{
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 把Object类存储到数据库
	 * @param object
	 */
	public static void save(Object object)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		
		s.save(object);
		s.getTransaction().commit();
	}
	
	public static void delete(Object ob)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		
		s.delete(ob);
		s.getTransaction().commit();
	}
	
	public static void delete(String id, Object object)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		
		s.delete(id, object);
		s.getTransaction().commit();
	}
	
	public static void update(Object ob)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		
		s.update(ob);
		s.getTransaction().commit();
	}
	
	public static Object get(Object object, String id)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		Object ob = s.get(object.getClass(), id);
		
		s.getTransaction().commit();
		
		return ob;
	}
	
	public static Query createQuery(String ql)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		
		Query q = s.createQuery(ql);
		
		s.getTransaction().commit();
		return q; 
	}
	
	public static Object createSQLQuery(Class c, String ql)
	{
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		
		Object ob = s.createSQLQuery(ql).addEntity(c).list();
		s.getTransaction().commit();
		return ob; 
	}
	
	public static void afterClass() {
		sessionFactory.close();
	}
}
