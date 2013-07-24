package org.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookData")
public class BookData
{
	private String barcode;
	private String status;
	private String collection;
	private Book book;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="callNumber")
	public Book getBook()
	{
		return book;
	}
	public void setBook(Book book)
	{
		this.book = book;
	}
	@Id
	@Column(name="id")
	public String getBarcode()
	{
		return barcode;
	}
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getCollection()
	{
		return collection;
	}
	public void setCollection(String collection)
	{
		this.collection = collection;
	}
	
}
