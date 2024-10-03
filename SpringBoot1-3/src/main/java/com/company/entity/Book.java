package com.company.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Book_Details")
public class Book {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Book_Id")
	private int bookId;
	@Column(name="Book_Title")
	private String bookTitle;
	@OneToOne(cascade=CascadeType.ALL)
	private Author author;
	public Book(int bookId, String bookTitle, Author author) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.author = author;
	}
	public Book() {
		super();
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", author=" + author + "]";
	}
	

}
