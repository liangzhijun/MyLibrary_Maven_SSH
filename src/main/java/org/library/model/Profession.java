package org.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="profession")
//@JsonIgnoreProperties (value = { "hibernateLazyInitializer" ,  "academy" })  
public class Profession
{
	private String id;
	private String profession;
	private String academyName;
	private int classlist;
	//@JsonIgnore
	private Academy academy;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="academyId")
	//@JsonIgnore
	public Academy getAcademy()
	{
		return academy;
	}
	public void setAcademy(Academy academy)
	{
		this.academy = academy;
	}
	@Id
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getProfession()
	{
		return profession;
	}
	public void setProfession(String profession)
	{
		this.profession = profession;
	}
	@Column(name="academy")
	public String getAcademyName()
	{
		return academyName;
	}
	public void setAcademyName(String academyName)
	{
		this.academyName = academyName;
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
