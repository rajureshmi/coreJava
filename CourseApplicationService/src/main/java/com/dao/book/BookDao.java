package com.dao.book;

import java.io.Serializable;
import java.time.LocalDate;

public class BookDao implements Serializable {

	private static final long serialVersionUID = 1786567956622638643L;

	private long bookId;
	private String title;
	private double price;
	private int volume;
	private LocalDate publishedDate;
	private long subjectId;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public BookDao(long bookId, String title, double price, int volume, LocalDate publishedDate, long subjectId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishedDate = publishedDate;
		this.subjectId = subjectId;
	}

}
