package com.example.demo.mapper.model;

public class BookInfo {
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookIntroduce;
	private int bookType;
	private float bookPrice;
	private String img;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookIntroduce() {
		return bookIntroduce;
	}

	public void setBookIntroduce(String bookIntroduce) {
		this.bookIntroduce = bookIntroduce;
	}

	public int getBookType() {
		return bookType;
	}

	public void setBookType(int bookType) {
		this.bookType = bookType;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}
