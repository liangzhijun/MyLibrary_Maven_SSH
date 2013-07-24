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
@Table(name="book")
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
	private int amount;
	private Set<BookData> bookData =new HashSet<BookData>();
	
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<BookData> getBookData()
	{
		return bookData;
	}
	public void setBookData(Set<BookData> bookData)
	{
		this.bookData = bookData;
	}
	@Id
	@Column(name="callNamber")
	public String getCallNumber()
	{
		return callNumber;
	}
	public void setCallNumber(String callNumber)
	{
		this.callNumber = callNumber;
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
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	
}
