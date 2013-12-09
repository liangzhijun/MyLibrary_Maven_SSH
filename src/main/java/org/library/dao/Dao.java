package org.library.dao;

import org.hibernate.Query;

public interface Dao
{
 	/**
	 * 把Object类存储到数据库
	 * @param object
	 */
	public void save(Object object);
	
	public void delete(Object ob);
	
	public void delete(String id, Object object);

	public void update(Object ob);
	
	public  Object get(Class c, String id);
	
	public Query createQuery(String ql);
	
	public Query createSQLQuery(Class c, String ql);
	
	
}
