package therollshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import therollshop.model.Admin;

public class AdminDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public AdminDao(Connection con) {
		this.con = con;
	}

	public Admin adminLogin(String admin_id, String password) {
		Admin admin = null;
		try {
			query = "select * from admins where a_id=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, admin_id);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setAdmin_id(rs.getString("a_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return admin;
	}
}
