package org.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="library")
public class Book
{
	private String title;
	private String author;
	private String publisher;
	private String callNumber;
	private String ISBNandPricing;
	private String subject;
	private String page;
	private String list;
	private String content;
	private String lib;
	private String barcode;
	private String condition;
	
	@Id
	@Column(name="barcode")
	public String getBarcode()
	{
		return barcode;
	}
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	public String getLib()
	{
		return lib;
	}
	public void setLib(String lib)
	{
		this.lib = lib;
	}
	public String getCondition()
	{
		return condition;
	}
	public void setCondition(String condition)
	{
		this.condition = condition;
	}
	public String getTitle()
	{
		return title;
	}
	public String getPage()
	{
		return page;
	}
	public void setPage(String page)
	{
		this.page = page;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getPublisher()
	{
		return publisher;
	}
	public void setPublisher(String publish)
	{
		this.publisher = publish;
	}
	public String getCallNumber()
	{
		return callNumber;
	}
	public void setCallNumber(String callNumber)
	{
		this.callNumber = callNumber;
	}
	public String getISBNandPricing()
	{
		return ISBNandPricing;
	}
	public void setISBNandPricing(String iSBNandPricing)
	{
		ISBNandPricing = iSBNandPricing;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public String getList()
	{
		return list;
	}
	public void setList(String list)
	{
		this.list = list;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
	
}
