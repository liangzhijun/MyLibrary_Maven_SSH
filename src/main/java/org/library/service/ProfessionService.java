package org.library.service;

import java.util.Map;
import java.util.Set;

public interface ProfessionService
{
	public Set<Map<String, String>> findAcademy(String academyId);
	
	public Map<String, Object> findProfession(String academyID,
			String professionId);
}
