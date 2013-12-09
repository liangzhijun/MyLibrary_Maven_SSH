package org.library.service;

import java.util.Map;
import java.util.Set;

import org.library.model.Profession;

public interface ProfessionService
{
	public Set<Map<String, String>> findAcademy(String academyId);
	
	public  Profession findProfession(String academyID,
			String professionId);
}
