package edu.miu.cs.cs544.exercises;

import java.time.LocalDate;
import java.util.Date;
import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="MY_BOOK")
public class Book {
	private int id;
	private String title;
	private String ISBN;
	private String author;
	private double price;
	private Date publish_date;
	public Book() {
	}

	

	public Book( String title, String iSBN, String author, double price,Date publish_date) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.price = price;
		this.publish_date = publish_date;
	}



	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Column
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	@Column
	public String getISBN() {
		return ISBN;
	}



	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	@Transient
	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}


	@Column
	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}


	@Temporal(TemporalType.DATE)
	public Date getPublish_date() {
		return publish_date;
	}



	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", ISBN=" + ISBN + ", author=" + author + ", price=" + price
				+ ", publish_date=" + publish_date + "]";
	}



	
}