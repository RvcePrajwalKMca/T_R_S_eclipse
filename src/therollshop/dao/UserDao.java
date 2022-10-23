package therollshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import therollshop.model.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}

	public User userLogin(String email, String password) {
		User user = null;
		try {
			query = "select * from users where email=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}

	public User userRegister(String name, String email, String password) {
		/*
		 * User user = null; try { query =
		 * "select count(*) val from users where email=?"; pst =
		 * this.con.prepareStatement(query); pst.setString(1, email); rs =
		 * pst.executeQuery(); if(rs.next()) { this.rowCount = rs.getInt("val"); }
		 * if(this.rowCount>0) { return user; } else { query =
		 * "insert into users (name,email,password) values(?,?,?)"; pst =
		 * this.con.prepareStatement(query); pst.setString(1, name); pst.setString(2,
		 * email); pst.setString(3, password); pst.executeQuery();
		 * 
		 * } }
		 */
		User user = null;
		try {
			query = "insert into users (name,email,password) values(?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, password);
			pst.executeUpdate();
			query = "select id from users where email=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
			}
			user.setName(name);
			user.setEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
}
