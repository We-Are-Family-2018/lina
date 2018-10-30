package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.form.RegisterForm;
import com.example.demo.mapper.model.UserInfo;
import com.example.demo.server.BookStoreServer;

@Controller
@CrossOrigin
@RequestMapping("/linaWeb")
public class WebController {
	
	@Autowired
	private BookStoreServer bookStoreServer;
	
    /**
     * 用户登入
     * @param name
     * @param password
     * @return
     */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String userName,String password) {
		return bookStoreServer.login(userName, password);
	}
	
	/**
	 * 用户注册
	 * @param form
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Object register(RegisterForm form) {
		if(bookStoreServer.register(form) != 1) {
			return false;
		};
		return true;
	}
	
	/**
	 * 图书搜索
	 * @param searchContent
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object searchByContent(String searchContent) {
		return bookStoreServer.bookSearch(searchContent);
	}
	
	/**
	 * 根据bookId查询
	 * @param searchId
	 * @return
	 */
	@RequestMapping("/searchBookById")
	@ResponseBody
	public Object searchByBookId(int bookId) {
		return bookStoreServer.searchByBookId(bookId);
	}
	
	/**
	 * 加入购物车
	 * @param UserId
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/addCard")
	@ResponseBody
	public Object addCard(int userId,int bookId) {			
		return bookStoreServer.addCard(userId, bookId);
	}
	
	/**
	 * 加入收藏
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/addCollect")
	@ResponseBody
    public Object addCollect(int userId,int bookId) {
    	return bookStoreServer.addCollect(userId, bookId);
    }
    
   /**
    * 我的购物车
    * @param userId
    * @return
    */
	@RequestMapping("/myCard")
	@ResponseBody
    public Object selectCard(int userId) {
    	return bookStoreServer.selectCard(userId);
    }
	
	@RequestMapping("/myCollect")
	@ResponseBody
    public Object myCollect(int userId) {
    	return bookStoreServer.selectCollect(userId);
    }
	
	/**
	 * 删除购物车
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/deleteCard")
	@ResponseBody
	 public Object deleteCard(int userId,int bookId) {
	    	return bookStoreServer.deleteCard(userId, bookId);
	}
	
	/**
	 * 删除收藏
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/deleteCollect")
	@ResponseBody
	 public Object deleteCollect(int userId,int bookId) {
	    	return bookStoreServer.deleteCollect(userId, bookId);
	}
	
	/**
	 * 添加地址
	 * @param userId
	 * @param address
	 * @return
	 */
	@RequestMapping("/addAddress")
	@ResponseBody
	 public Object addAddress(int userId,String address) {
	    	return bookStoreServer.addAddress(userId, address);
	}
	
	/**
	 * 我的地址
	 * @param userId
	 * @return
	 */
	@RequestMapping("/myAddress")
	@ResponseBody
    public Object myAddress(int userId) {
    	return bookStoreServer.selectAddress(userId);
    }
	
	/**
	 * 删除地址
	 * @param userId
	 * @return
	 */
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public Object deleteAddress(int userId,int addressId) {
    	return bookStoreServer.deleteAddress(userId, addressId);
    }
	
	/**
	 * 添加订单
	 * @param userId
	 * @param bookId
	 * @param number
	 * @return
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	 public Object addOrder(int userId,int bookId,int number) {
	    	return bookStoreServer.addOrder(userId, bookId, number);
	}
	
	/**
	 * 我的订单
	 * @param userId
	 * @return
	 */
	@RequestMapping("/myOrder")
	@ResponseBody
	 public Object myOrder(int userId) {
	    	return bookStoreServer.MyOrder(userId);
	}
	
	/**
	 * 删除订单
	 * @param userId
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	@ResponseBody
	 public Object deleteOrder(int orderId) {
	    	return bookStoreServer.deleteOrder(orderId);
	}
	
	/**
	 * 个人信息修改
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	 public Object updateUserInfo(UserInfo userInfo) {
		 
		 return bookStoreServer.updateUserInfo(userInfo);
	 }
     
	 /**
	  * 查询所有书籍
	  * @return
	  */
	@RequestMapping("/seleteAllBook")
	@ResponseBody
     public Object seleteAllBook(Integer type) {
    	 return bookStoreServer.selectAllBook(type);
     }
	
	/**
	 * 待付款数据
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequestMapping("/waitePay")
	@ResponseBody
	public Object waitePay(int userId) {
   	 return bookStoreServer.waitePayBook(userId);
    }
	
	/**
	 * 待收获数据
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequestMapping("/waiteGetBook")
	@ResponseBody
	public Object waiteGetBook(int userId) {
  	 return bookStoreServer.waiteGetBook(userId);
   }
	
	/**
	 * 已收获数据
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequestMapping("/yetGetBook")
	@ResponseBody
	public Object yetGetBook(int userId) {
  	 return bookStoreServer.yetGetBook(userId);
   }
	
	/**
	 * 
	 * @param userId
	 * @param ordreId
	 * @return
	 */
	@RequestMapping("/confirmOrder")
	@ResponseBody
   public Object confirmOrder(int userId,int orderId) {
	   return bookStoreServer.confirmOrder(userId,orderId);
   }	
   
}
