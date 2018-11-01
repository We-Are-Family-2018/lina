/**
 * 
 */
package com.example.demo.form;

/**
 * @author semon
 * @created 2018年10月30日
 * @since 
 * 
 */
public class SearchBookForm {
	private Integer bookType;
	
	private String bookName;
	
	private Integer page;
	
	private Integer limit;

	public Integer getBookType() {
		return bookType;
	}

	public void setBookType(Integer bookType) {
		this.bookType = bookType;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getPage() {
		return page != null ? page : 1;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit != null ? limit : 10;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
