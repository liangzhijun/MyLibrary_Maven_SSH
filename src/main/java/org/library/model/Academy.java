package org.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="academy")
public class Academy
{
	private String id;
	private String name;
	private Set<Profession> professions = new HashSet<Profession>();
	
	@Id
	@Column(name="id")
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	@Column(name="name")
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@OneToMany(mappedBy="academy", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<Profession> getProfessions()
	{
		return professions;
	}
	public void setProfessions(Set<Profession> professions)
	{
		this.professions = professions;
	}
	
}
