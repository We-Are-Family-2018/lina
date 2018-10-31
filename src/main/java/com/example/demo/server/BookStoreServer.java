package com.example.demo.server;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.RegisterForm;
import com.example.demo.form.SearchBookForm;
import com.example.demo.form.SearchOrderForm;
import com.example.demo.mapper.BookStoreMapper;
import com.example.demo.mapper.model.Address;
import com.example.demo.mapper.model.BookInfo;
import com.example.demo.mapper.model.MyCard;
import com.example.demo.mapper.model.Order;
import com.example.demo.mapper.model.OrderExtra;
import com.example.demo.mapper.model.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BookStoreServer {
	
	@Autowired
	private BookStoreMapper bookStoreMapper;

	public int login(String userName,String password) {
		
		UserInfo userInfo = bookStoreMapper.selectUserInfo(userName,password);
		
		if(userInfo == null) {
			return 1;
		}		     
		return userInfo.getUserId();
	}
	
	public int register(RegisterForm form) {
		return bookStoreMapper.insert(form);
	}
	
	public List<BookInfo> bookSearch(String searchContent) {
		return bookStoreMapper.selectBookInfo(searchContent);
	}
	
	public BookInfo searchByBookId(int bookId) {
		return bookStoreMapper.selectBookById(bookId);
	}
	
	/**
	 * 加入购物车
	 */
	public int addCard(int userId,int bookId) {
		Date createDt = new Date();		
		return bookStoreMapper.insertCard(userId, bookId, createDt);
	}
	
	/**
	 * 加入收藏
	 */
	public int addCollect(int userId,int bookId) {
		Date createDt = new Date();		
		return bookStoreMapper.insertCollcet(userId, bookId, createDt);
	}
	
	/**
	 * 我的购物车
	 * @return
	 */
    public List<MyCard> selectCard(int userId){ 	   	
    	return bookStoreMapper.selectMyCard(userId);
    }
    
    /**
     * 我的收藏
     * @param userId
     * @return
     */
    
    public List<MyCard> selectCollect(int userId){ 	   	
    	return bookStoreMapper.selectMyCollect(userId);
    }
    
    /**
     * 删除购物车
     * @param userId
     * @param bookId
     * @return
     */
    public int deleteCard(int userId,int bookId){ 	   	
    	return bookStoreMapper.deleteCard(userId, bookId);
    }
    
    /**
     * 删除收藏
     * @param userId
     * @param bookId
     * @return
     */
    public int deleteCollect(int userId,int bookId) {	
		return bookStoreMapper.deleteCollect(userId, bookId);
	}
    
    /**
     * 添加地址
     * @param userId
     * @param address
     * @return
     */
    
    public int addAddress(int userId,String address) {
    	Date createDt = new Date();
    	return bookStoreMapper.insertAddress(userId, address, createDt);
    }
    
    /**
     * 我的地址
     * @param userId
     * @return
     */
    public List<Address> selectAddress(int userId){ 	   	
    	return bookStoreMapper.selectMyAddress(userId);
    }
    
    /**
     * 删除地址
     * @param userId
     * @return
     */
    public int deleteAddress(int userId,int addressId){ 	   	
    	return bookStoreMapper.deleteAddress(userId,addressId);
    }
    
    /**
     * 添加订单
     * @param userId
     * @param bookId
     * @return
     */
    public int addOrder(int userId,int bookId, int number) {
    	Date createDt = new Date();		
		return bookStoreMapper.insertOrder(userId, bookId, number, createDt);
    }
    
    /**
     * 我的订单
     * @param userId
     * @return
     */
    public List<Order> MyOrder(int userId) {
		return bookStoreMapper.selectMyOrder(userId);
    }
    
    /**
     * 删除订单
     * @param userId
     * @return
     */
    public int deleteOrder(int orderId) {
		return bookStoreMapper.deleteOrder(orderId);
    }
    
    /**
     * 书籍上架
     * @param bookInfo
     * @return
     */
    public int addBook(BookInfo bookInfo) {
    	return bookStoreMapper.insertBook(bookInfo);
    }
    
    /**
     * 书籍下架
     * @param bookId
     * @return
     */
    public int deleteBook(int bookId) {
    	return bookStoreMapper.deleteBook(bookId);
    }
    
    /**
     * 书籍编辑
     * @param bookInfo
     * @return
     */
    public int editBook(BookInfo bookInfo) {   	
    	return bookStoreMapper.updateBookSelective(bookInfo);
    }
    
    public List<BookInfo> selectAllBook(Integer type) {
    	if(type == null) {
    		return bookStoreMapper.selectAllBook();
    	}
    	else {
    		return bookStoreMapper.selectTypeAllBook(type);
    	}
    }
    
    public PageInfo<BookInfo> searchBook(SearchBookForm form) {
    	PageHelper.startPage(form.getPage(), form.getLimit());
    	List<BookInfo> bookInfos = bookStoreMapper.selectBook(form.getBookType(), form.getBookName());
    	PageInfo<BookInfo> bookInfoPage = new PageInfo<>(bookInfos);
    	
    	return bookInfoPage;
    }
    
    /**
     * 查询所有用户
     * @return
     */
    public List<UserInfo> selectAllUser(){
    	return bookStoreMapper.selectAllUser();
    }
    
    /**
     * 查询所有订单
     * @return
     */
    public List<Order> selectAllOrder(){
    	return bookStoreMapper.selectAllOrder();
    }
    
    /**
     * 搜索订单
     * @return
     */
    public PageInfo<OrderExtra> searchOrder(SearchOrderForm form){
    	PageHelper.startPage(form.getPage(), form.getLimit());
    	List<OrderExtra> orderInfos = bookStoreMapper.selectOrder(form.getOrderStatus());
    	PageInfo<OrderExtra> orderInfoPage = new PageInfo<>(orderInfos);
    	
    	return orderInfoPage;
    }
    
    /**
     * 编辑订单
     * @param orderStatus
     * @param orderId
     * @return
     */
    public int updateOrderEdit(int orderStatus,int orderId) {
    	return bookStoreMapper.updateOrder(orderStatus, orderId); 
    }
    
    /**
     * 更新用户信息
     * @param orderStatus
     * @param orderId
     * @return
     */
    public int updateUserInfo(UserInfo userInfo) {
    	return bookStoreMapper.updateUserInfo(userInfo); 
    }
    
    /**
     * 待支付
     * @param userId
     * @param orderId
     * @return
     */
    public List<Order> waitePayBook(int userId){
    	return bookStoreMapper.waitePayBook(userId);
    }
    
    /**
     * 带收货
     * @param userId
     * @param orderId
     * @return
     */
    public List<Order> waiteGetBook(int userId){
    	return bookStoreMapper.waiteGetBook(userId);
    }
    
    /**
     * 已收货
     * @param userId
     * @param orderId
     * @return
     */
    public List<Order> yetGetBook(int userId){
    	return bookStoreMapper.yetGetBook(userId);
    }
    
    /**
     * 确认收货
     * @param userId
     * @param orderId
     * @return
     */
    public int confirmOrder(int userId,int orderId) {
    	return bookStoreMapper.confirmOrder(userId,orderId);
    }
    
}
