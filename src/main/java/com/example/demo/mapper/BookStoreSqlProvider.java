/**
 * 
 */
package com.example.demo.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.example.demo.mapper.model.BookInfo;

/**
 * @author semon
 * @created 2018年10月31日
 * @since 
 * 
 */
public class BookStoreSqlProvider {
	
	public String updateBookSelective(BookInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("book");
        
        if (record.getBookName() != null) {
            sql.SET("book_name = #{bookName,jdbcType=VARCHAR}");
        }
        
        if (record.getBookAuthor() != null) {
            sql.SET("book_author = #{bookAuthor,jdbcType=VARCHAR}");
        }
        
        if (record.getBookIntroduce() != null) {
            sql.SET("book_introduce = #{bookIntroduce,jdbcType=VARCHAR}");
        }
        
        if (record.getImg() != null) {
            sql.SET("book_img = #{img,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("book_id = #{bookId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
	
	public String selectBook(Integer bookType, String bookName) {
		SQL sql = new SQL();
		sql.SELECT("*");
		sql.FROM("book");

		if (bookType != null) {
			sql.WHERE("book_type = #{bookType,jdbcType=INTEGER}");
		}
		
		if (bookName != null && StringUtils.hasText(bookName)) {
			sql.WHERE("book_name like '%" + bookName + "%'");
		}
		
		return sql.toString();
	}
	
	public String selectOrder(Integer orderStatus) {
		SQL sql = new SQL();
		sql.SELECT("my_order.*");
		sql.SELECT("user.user_name");
		sql.SELECT("book.book_name");
		sql.SELECT("book.book_price");
		sql.SELECT("book.book_img");
		sql.SELECT("book.book_introduce");
		
		sql.FROM("my_order");
		sql.LEFT_OUTER_JOIN("book on book.book_id = my_order.book_id");
		sql.LEFT_OUTER_JOIN("user on user.user_id = my_order.user_id");
		
		if (orderStatus != null) {
			sql.WHERE("order_status = #{orderStatus,jdbcType=INTEGER}");
		}
		
		return sql.toString();
	}
}
