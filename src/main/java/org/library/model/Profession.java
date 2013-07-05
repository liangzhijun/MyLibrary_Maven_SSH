package org.library.model;

public class Profession
{
	private String profession;
	private String id;
	private String academyId;
	private String academy;
	private int classlist;
	
	public String getProfession()
	{
		return profession;
	}
	public void setProfession(String profession)
	{
		this.profession = profession;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getAcademyId()
	{
		return academyId;
	}
	public void setAcademyId(String academyId)
	{
		this.academyId = academyId;
	}
	public String getAcademy()
	{
		return academy;
	}
	public void setAcademy(String academy)
	{
		this.academy = academy;
	}
	public int getClasslist()
	{
		return classlist;
	}
	public void setClasslist(int classlist)
	{
		this.classlist = classlist;
	}
	
}
