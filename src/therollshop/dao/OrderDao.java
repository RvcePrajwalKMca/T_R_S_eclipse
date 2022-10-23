package therollshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import therollshop.model.*;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDao(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order model) {
		boolean result = false;
		try {
			query = "insert into orders (i_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Order> userOrders(int id) {
		List<Order> list = new ArrayList<>();
		try {
			query = "select * from orders where u_id=? order by orders.o_id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				ItemDao itemDao = new ItemDao(this.con);
				int iId = rs.getInt("i_id");

				Item item = itemDao.getSingleItem(iId);
				order.setOrderId(rs.getInt("o_id"));
				order.setId(iId);
				order.setName(item.getName());
				order.setPrice(item.getPrice() * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void cancelOrder(int id) {
		try {
			query = "delete from orders where o_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
