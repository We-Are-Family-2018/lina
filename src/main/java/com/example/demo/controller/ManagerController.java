package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.mapper.model.BookInfo;
import com.example.demo.server.BookStoreServer;

@Controller
@CrossOrigin
@RequestMapping("/managerWeb")
public class ManagerController {
   
	@Autowired
	private BookStoreServer bookStoreServer;
	
	/**
	 * 书籍上架
	 * @param bookInfo
	 * @return
	 */
	@RequestMapping("/addBook")
	public Object addBook(BookInfo bookInfo) {
		return bookStoreServer.addBook(bookInfo);
	}
	
	/**
	 * 书籍下架
	 * @param bookInfo
	 * @return
	 */
	@RequestMapping("/deleteBook")
	public Object deleteBook(int bookId) {
		return bookStoreServer.deleteBook(bookId);
	}
	
	/**
	 * 书籍编辑
	 * @param bookInfo
	 * @return
	 */
	public Object editBook(BookInfo bookInfo) {
		return bookStoreServer.editBook(bookInfo);
	}
	
	/**
	 * 查询所有书籍
	 * @return
	 */
	public Object selectAllBook(int type) {
		return bookStoreServer.selectAllBook(type);
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public Object selectAllUser() {
		return bookStoreServer.selectAllUser();
	}
	
	/**
	 * 编辑所有订单
	 * @return
	 */
	public Object selectAllOrder(int orderStatus,int orderId) {
		return bookStoreServer.updateOrderEdit(orderStatus, orderId);
	}
	
}
