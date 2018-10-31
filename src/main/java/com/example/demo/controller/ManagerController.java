package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.form.SearchBookForm;
import com.example.demo.form.SearchOrderForm;
import com.example.demo.mapper.model.BookInfo;
import com.example.demo.mapper.model.Order;
import com.example.demo.mapper.model.OrderExtra;
import com.example.demo.mapper.model.PageData;
import com.example.demo.server.BookStoreServer;
import com.github.pagehelper.PageInfo;

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
	@ResponseBody
	public Object addBook(BookInfo bookInfo) {
		bookStoreServer.addBook(bookInfo);
		return bookInfo;
	}
	
	/**
	 * 书籍下架
	 * @param bookInfo
	 * @return
	 */
	@RequestMapping("/deleteBook")
	@ResponseBody
	public Object deleteBook(int bookId) {
		return bookStoreServer.deleteBook(bookId);
	}
	
	/**
	 * 书籍编辑
	 * @param bookInfo
	 * @return
	 */
	@RequestMapping("/updateBook")
	@ResponseBody
	public Object editBook(BookInfo bookInfo) {
		return bookStoreServer.editBook(bookInfo);
	}
	
	/**
	 * 查询所有书籍
	 * @return
	 */
	@RequestMapping("/searchBook")
	@ResponseBody
	public Object searchBook(SearchBookForm form) {
		PageInfo<BookInfo> page = bookStoreServer.searchBook(form);
		PageData data = new PageData(page.getTotal(), page.getList());
		return data;
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public Object selectAllUser() {
		return bookStoreServer.selectAllUser();
	}
	
	/**
	 * 查找订单
	 * @return
	 */
	@RequestMapping("/searchOrder")
	@ResponseBody
	public Object searchOrder(SearchOrderForm form) {
		PageInfo<OrderExtra> pageInfo = bookStoreServer.searchOrder(form);
		PageData data = new PageData(pageInfo.getTotal(), pageInfo.getList());
		return data;
	}
	
	@RequestMapping("/uploadBookImg")
	@ResponseBody
	public Object uploadImg(@RequestParam(value="bookId", required=false) Integer bookId, 
			@RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
		// 生成新名字
		String fileName = generateNewName(file.getOriginalFilename());
		
		// 保存到文件系统
		String imgPath = "external/img/" + fileName;
		Path path = Paths.get(imgPath);
		Files.write(path, file.getBytes());
		
		// 生成图片url
		String imgUrl = "/static/img/" + fileName;
		
		// 如果id存在则更新相应图书的封面url
		if (bookId != null) {
			BookInfo book = bookStoreServer.searchByBookId(bookId);
			book.setImg(imgUrl);
			bookStoreServer.editBook(book);
		}
		
		// 返回数据
		Map<String, Object> map = new HashMap<>();
		map.put("img", imgUrl);
		
		return map;
	}

	private String generateNewName(String fileName) throws NoSuchAlgorithmException {
		// 获取文件后缀名
		String[] tempArr = fileName.split("\\.");
		String imgType = tempArr[tempArr.length - 1];
		
		// 生成文件名
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String temp = LocalDateTime.now().toString() + fileName;
		String newName = DatatypeConverter.printHexBinary(md5.digest(temp.getBytes())) + "." + imgType;
		return newName;
	}
}
