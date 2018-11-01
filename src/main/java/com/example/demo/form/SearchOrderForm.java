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
public class SearchOrderForm {
	private Integer orderStatus;
	
	private Integer page;
	
	private Integer limit;

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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
