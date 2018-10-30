package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mapper.model.BookInfo;
import com.example.demo.server.BookStoreServer;

@Controller
@CrossOrigin
@RequestMapping("/managerWeb")
public class ManagerController {
	private static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);
	
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
	
	@RequestMapping("/uploadBookImg")
	@ResponseBody
	@CrossOrigin("*")
	public Object uploadImg(@RequestParam(value="bookId", required=false) Integer bookId, 
			@RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
		// 获取文件后缀名
		String[] tempArr = file.getOriginalFilename().split("\\.");
		String imgType = tempArr[tempArr.length - 1];
		
		// 生成文件名
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String temp = LocalDateTime.now().toString() + file.getOriginalFilename();
		String fileName = DatatypeConverter.printHexBinary(md5.digest(temp.getBytes())) + "." + imgType;
		
		// 保存路径
		String imgPath = "external/img/" + fileName;
		Path path = Paths.get(imgPath);
		Files.write(path, file.getBytes());
		
		// 返回数据
		Map<String, Object> map = new HashMap<>();
		map.put("img", "/static/img/" + fileName);
		
		return map;
	}
}
