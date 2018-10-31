package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.form.RegisterForm;
import com.example.demo.mapper.model.Address;
import com.example.demo.mapper.model.BookInfo;
import com.example.demo.mapper.model.MyCard;
import com.example.demo.mapper.model.Order;
import com.example.demo.mapper.model.OrderExtra;
import com.example.demo.mapper.model.UserInfo;

public interface BookStoreMapper {
     /**
      * 查询用户信息
      * @param userName
      * @param password
      * @return
      */
	 @Select({
	        "select",
	        "*",
	        "from user",
	        "where user_name = #{userName,jdbcType=VARCHAR} and password=#{password,jdbcType=VARCHAR}"
	    })
	    @Results({
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
	        @Result(column="Telphone", property="Telphone", jdbcType=JdbcType.VARCHAR),
	        @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
	    })
	    UserInfo selectUserInfo(@Param("userName") String userName, @Param("password")String password);
	 
	 /**
	  * 用户注册
	  * @param form
	  * @return
	  */
	 @Insert({
	        "insert into user",
	        "(",
	        "user_name,password,Telphone,mail",
	        ")", 
	        "values (#{userName,jdbcType=VARCHAR},",
	        "#{password,jdbcType=VARCHAR}, #{Telphone,jdbcType=VARCHAR},",
	        "#{mail,jdbcType=VARCHAR})"
	    })
	    int insert(RegisterForm form);
	 
	 /**
	  * 查询书籍信息
	  * @param searchContent
	  * @return
	  */
	 @Select({
	        "select",
	        "*",
	        "from book",
	        "where book_name = #{searchContent,jdbcType=VARCHAR}"
	    })
	    @Results({
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_author", property="bookAuthor", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR)
	    })
	    List<BookInfo> selectBookInfo(@Param("searchContent") String searchContent);
	 /**
	  * 查询书籍信息
	  * @param bookId
	  * @return
	  */
	 @Select({
	        "select",
	        "*",
	        "from book",
	        "where book_id = #{bookId,jdbcType=INTEGER}"
	    })
	    @Results({
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_author", property="bookAuthor", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR)
	    })
	    BookInfo selectBookById(@Param("bookId") int bookId);
	 
	 /**
	  * 加入购物车
	  * @param userId
	  * @param bookId
	  * @param createDt
	  * @return
	  */
	 @Insert({
	        "insert into card",
	        "(",
	        "user_id,book_id,create_dt",
	        ")", 
	        "values (#{userId,jdbcType=INTEGER},",
	        "#{bookId,jdbcType=INTEGER}, #{createDt,jdbcType=TIMESTAMP})"
	    })
	    int insertCard(@Param("userId") int userId,@Param("bookId") int bookId,@Param("createDt") Date createDt);
	 
	 /**
	  * 加入收藏
	  * @param userId
	  * @param bookId
	  * @param createDt
	  * @return
	  */
	 @Insert({
	        "insert into collect",
	        "(",
	        "user_id,book_id,create_dt",
	        ")", 
	        "values (#{userId,jdbcType=INTEGER},",
	        "#{bookId,jdbcType=INTEGER}, #{createDt,jdbcType=TIMESTAMP})"
	    })
	    int insertCollcet(@Param("userId") int userId,@Param("bookId") int bookId,@Param("createDt") Date createDt);
	 
	 /**
	  * 我的购物车
	  * @param userId
	  * @return
	  */
	 @Select({
		 "select c.book_id,c.number,c.user_id,b.book_img,b.book_name,b.book_price,b.book_introduce" ,
		 "from card c INNER JOIN book b on c.book_id = b.book_id",
		 "where c.user_id = #{userId,jdbcType=INTEGER} ORDER BY c.create_dt"
	    })
	 @Results({
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.INTEGER),
	    })
	    List<MyCard> selectMyCard(@Param("userId") int userId);
	 
	 /**
	  * 我的收藏
	  * @param userId
	  * @return
	  */
	 @Select({
		 "select c.book_id,c.user_id,b.book_img,b.book_name,b.book_price " ,
		 "from collect c INNER JOIN book b on c.book_id = b.book_id",
		 "where c.user_id = #{userId,jdbcType=INTEGER} ORDER BY c.create_dt"
	    })
	 @Results({
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	    })
	    List<MyCard> selectMyCollect(@Param("userId") int userId);
	 
	 /**
	  * 删除购物车
	  * @param userId
	  * @param bookId
	  * @return
	  */
	 @Delete({
	        "delete from card",
	        "where user_id = #{userId,jdbcType=INTEGER} and book_id = #{bookId,jdbcType=INTEGER}"
	    })
	    int deleteCard(@Param("userId") int userId,@Param("bookId") int bookId);
	 
	 /**
	  * 删除收藏
	  * @param userId
	  * @param bookId
	  * @return
	  */
	 @Delete({
	        "delete from collect",
	        "where user_id = #{userId,jdbcType=INTEGER} and book_id = #{bookId,jdbcType=INTEGER}"
	    })
	    int deleteCollect(@Param("userId") int userId,@Param("bookId") int bookId);
	 
	 
	 /**
	  * 添加地址
	  * @param userId
	  * @param address
	  * @param createDt
	  * @return
	  */
	 @Insert({
	        "insert into address",
	        "(",
	        "user_id,address,create_dt",
	        ")", 
	        "values (#{userId,jdbcType=INTEGER},",
	        "#{address,jdbcType=VARCHAR}, #{createDt,jdbcType=TIMESTAMP})"
	    })
	    int insertAddress(@Param("userId") int userId,@Param("address") String address,@Param("createDt") Date createDt);
     
	 /**
	  * 我的地址
	  * @param userId
	  * @return
	  */
	 @Select({
		 "select address,address_id from address where user_id = #{userId,jdbcType=VARCHAR}"
	    })
	 @Results({
	        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR, id=true),
	        @Result(column="address_id", property="addressId", jdbcType=JdbcType.INTEGER, id=true),
	    })
	    List<Address> selectMyAddress(@Param("userId") int userId);
	 
	 /**
	  * 删除地址
	  * @param userId
	  * @param addressId
	  * @return
	  */
	 @Delete({
	        "delete from address",
	        "where user_id = #{userId,jdbcType=INTEGER} and address_id = #{addressId,jdbcType=INTEGER}"
	    })
	    int deleteAddress(@Param("userId") int userId,@Param("addressId") int addressId);
	 
	 /**
	  * 添加订单
	  * @param userId
	  * @param bookId
	  * @param number
	  * @param createDt
	  * @return
	  */
	 @Insert({
	        "insert into my_order",
	        "(",
	        "user_id,book_id,number,create_dt,order_status",
	        ")", 
	        "values (#{userId,jdbcType=INTEGER},",
	        "#{bookId,jdbcType=INTEGER},#{number,jdbcType=INTEGER},#{createDt,jdbcType=TIMESTAMP},0)"
	    })
	    int insertOrder(@Param("userId") int userId,@Param("bookId") int bookId,@Param("number") int number,@Param("createDt") Date createDt);
	 
	 /**
	  * 我的订单
	  * @param userId
	  * @return
	  */
	 @Select({
		 "select o.order_id,o.book_id,o.number,o.user_id,o.order_status,b.book_img,b.book_name,b.book_price,b.book_introduce" ,
		 "from my_order o INNER JOIN book b on o.book_id = b.book_id",
		 "where o.user_id = #{userId,jdbcType=INTEGER} ORDER BY o.create_dt"
	    })
	 @Results({
		    @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.INTEGER),
	    })
	    List<Order> selectMyOrder(@Param("userId") int userId);
	 
	 /**
	  * 删除订单
	  * @param userId
	  * @param orderId
	  * @return
	  */
	 @Delete({
	        "delete from my_order",
	        "where user_id =order_id = #{orderId,jdbcType=INTEGER}"
	    })
	    int deleteOrder(@Param("orderId") int orderId);
	 
	 @Insert({
	        "insert into book",
	        "(",
	        "book_name,book_author,book_introduce,book_type,book_img,book_price",
	        ")", 
	        "values (#{bookName,jdbcType=VARCHAR},#{bookAuthor,jdbcType=VARCHAR},#{bookIntroduce,jdbcType=VARCHAR},",
	        "#{bookType,jdbcType=INTEGER},#{img,jdbcType=VARCHAR},#{bookPrice,jdbcType=FLOAT})"
	    })
	 @Options(useGeneratedKeys = true, keyProperty = "bookId", keyColumn = "book_id")
	    int insertBook(BookInfo bookInfo);
	 
	 @Delete({
	        "delete from book",
	        "where user_id = #{bookId,jdbcType=INTEGER}"
	    })
	    int deleteBook(@Param("bookId") int bookId);
	 
	 @Update({
	        "update book",
	        "set book_name = #{bookName,jdbcType=VARCHAR},",
	          "book_author = #{bookAuthor,jdbcType=VARCHAR},",
	          "book_introduce = #{bookIntroduce,jdbcType=VARCHAR},",
	          "book_type = #{bookType,jdbcType=INTEGER},",
	          "book_img = #{img,jdbcType=VARCHAR},",
	          "book_price = #{bookPrice,jdbcType=FLOAT}",
	        "where book_id = #{bookId,jdbcType=INTEGER}"
	    })
    int updateBook(BookInfo bookInfo);
	 
	 @UpdateProvider(type=BookStoreSqlProvider.class, method="updateBookSelective")
	 int updateBookSelective(BookInfo bookInfo);
	 
	 @Select({
		 "select * from book where book_type = #{type,jdbcType=INTEGER}",
	    })
	 @Results({
		 @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_author", property="bookAuthor", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR)
	    })
	    List<BookInfo> selectTypeAllBook(@Param("type") int type);
	 
	 @Select({
		 "select * from book",
	    })
	 @Results({
		    @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_author", property="bookAuthor", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR)
	    })
	    List<BookInfo> selectAllBook();
	 
	 @SelectProvider(type=BookStoreSqlProvider.class, method="selectBook")
	 @Results({
		    @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_author", property="bookAuthor", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.FLOAT),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR)
	    })
	 List<BookInfo> selectBook(@Param("bookType") Integer bookType, @Param("bookName") String bookName);
	 
	 @Select({
		 "select * from user",
	    })
	 @Results({
		 @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
	        @Result(column="Telphone", property="Telphone", jdbcType=JdbcType.VARCHAR),
	        @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR)
	    })
	    List<UserInfo> selectAllUser(); 
	 
	 @Select({
		 "select o.order_id,o.book_id,o.number,o.user_id,o.order_status,b.book_img,b.book_name,b.book_price " ,
		 "from my_order o INNER JOIN book b on o.book_id = b.book_id",
		 "ORDER BY o.create_dt"
	    })
	 @Results({
		    @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
	    })
	    List<Order> selectAllOrder();
	 
	 @SelectProvider(type=BookStoreSqlProvider.class, method="selectOrder")
	 @Results({
		    @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="book_introduce", property="bookIntroduce", jdbcType=JdbcType.VARCHAR),
	        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
	    })
	 List<OrderExtra> selectOrder(@Param("orderStatus") Integer orderStatus);
	 
	 @Update({
	        "update order",
	        "set order_status = #{orderStatus,jdbcType=INTEGER} where order_id = #{orderId,jdbcType=INTEGER}",
	    })
	    int updateOrder(int orderStatus,int orderId);
	 
	 @Update({
	        "update user",
	        "set user_name = #{userName,jdbcType=VARCHAR},", 
	        "password = #{password,jdbcType=VARCHAR},", 
	        "Telphone = #{Telphone,jdbcType=VARCHAR},", 	
	        "mail = #{mail,jdbcType=VARCHAR}", 
	        "where user_id = #{userId,jdbcType=INTEGER}",
	    })
	    int updateUserInfo(UserInfo userInfo);
	 
	 /**
	  * 待付款
	  * @return
	  */
	 @Select({
		 "select o.order_id,o.book_id,o.number,o.user_id,o.order_status,b.book_img,b.book_name,b.book_price " ,
		 "from my_order o INNER JOIN book b on o.book_id = b.book_id",
		 "where o.order_status = 0 and user_id = #{userId,jdbcType=INTEGER}",
		 "ORDER BY o.create_dt"
	    })
	 @Results({
		    @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
	    })
	    List<Order> waitePayBook(int userId);
	 
	 /**
	  * 待收货
	  * @return
	  */
	 @Select({
		 "select o.order_id,o.book_id,o.number,o.user_id,o.order_status,b.book_img,b.book_name,b.book_price " ,
		 "from my_order o INNER JOIN book b on o.book_id = b.book_id",
		 "where o.order_status = 1 and user_id = #{userId,jdbcType=INTEGER}",
		 "ORDER BY o.create_dt"
	    })
	 @Results({
		    @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
	    })
	    List<Order> waiteGetBook(int userId);
	 
	 /**
	  * 以收货
	  * @return
	  */
	 @Select({
		 "select o.order_id,o.book_id,o.number,o.user_id,o.order_status,b.book_img,b.book_name,b.book_price " ,
		 "from my_order o INNER JOIN book b on o.book_id = b.book_id",
		 "where o.order_status = 2 and user_id = #{userId,jdbcType=INTEGER}",
		 "ORDER BY o.create_dt"
	    })
	 @Results({
		    @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
	        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_img", property="img", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="book_price", property="bookPrice", jdbcType=JdbcType.INTEGER),
	        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
	    })
	    List<Order> yetGetBook(int userId);
	 
	 
	 @Update({
	        "update my_order",
	        "set order_status = 2",
	        "where user_id = #{userId,jdbcType=INTEGER} and order_id = #{orderId,jdbcType=INTEGER}",
	    })
	 int confirmOrder(@Param("userId")int userId,@Param("orderId")int orderId);
	
}
