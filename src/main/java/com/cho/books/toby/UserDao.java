package com.cho.books.toby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/d:/dev/h2-data/books/toby", "sa", "");
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES(?, ?, ?)");
        ps.setString(1, user.getId      ());
        ps.setString(2, user.getName    ());
        ps.setString(3, user.getPassword());
        
        ps.execute();
        
        ps.close();
        conn.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/d:/dev/h2-data/books/toby", "sa", "");

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId      (rs.getString("id"      ));
        user.setName    (rs.getString("name"    ));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao userDao = new UserDao();
		
		User user = new User();
		user.setId("cho");
		user.setName("조");
		user.setPassword("1234");
		
		userDao.add(user);
		
		System.out.println("등록 성공");
		
		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회 성공");
		
		
	}
}
