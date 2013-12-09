package org.library.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.library.dao.Dao;
import org.library.model.Academy;
import org.library.model.Profession;
import org.library.service.ProfessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("professionService")
@Transactional
public class ProfessionServiceImpl implements ProfessionService
{
	private Dao dao;
	
	@Resource(name="dao")
	public void setDao(Dao dao)
	{
		this.dao = dao;
	}

	/**
	 * 根据academy(学院ID)搜索学院，返回一个包含学院内所有专业名称专业ID和的集合
	 * @param academy
	 * @return
	 */
	@Override
	public Set<Map<String, String>> findAcademy(String academyId)
	{
		Set<Map<String, String>> set = new HashSet<Map<String, String>>();
		
		 Academy academy = (Academy)dao.get(Academy.class, academyId);	//以id和引用作索引查询数据库，返回一个Object类型
		
		for(Profession pro: academy.getProfessions())
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", pro.getId());
			map.put("profession", pro.getProfession());
			
			set.add(map);
			
			System.out.println("id:" + pro.getId() +", profession:"+ pro.getProfession());
		}
		
		return set;
	}

	/**
	 * 根据professionId(专业ID)搜索，把专业的班级数量classlist和单位信息unit放到Map里，然后返回
	 * @param professionId
	 * @return
	 */
	@Override
	public Profession findProfession(String academyID,
			String professionId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		return (Profession)dao.get(Profession.class, professionId);
	}

}
