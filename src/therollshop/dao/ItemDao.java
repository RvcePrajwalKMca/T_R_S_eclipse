package therollshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import therollshop.model.*;

public class ItemDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ItemDao(Connection con) {
		this.con = con;
	}

	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		try {
			query = "select * from items";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Item row = new Item();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getDouble("price"));
				row.setDescription(rs.getString("description"));
				row.setStock(rs.getInt("stock"));
				row.setImage(rs.getString("image"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	public List<Bucket> getBucketItems(ArrayList<Bucket> bucketList) {
		List<Bucket> items = new ArrayList<Bucket>();

		try {
			if (bucketList.size() > 0) {
				for (Bucket item : bucketList) {
					query = "select * from items where id = ?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Bucket row = new Bucket();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setStock(rs.getInt("stock"));
						row.setQuantity(item.getQuantity());
						items.add(row);

					}

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return items;
	}
	
	public Item getSingleItem(int id) {
		Item row = null;
		try {
			query = "select * from items where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				row = new Item();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	public double gettotalCartPrice(ArrayList<Bucket> bucketList) {
		double sum = 0;
		try {
			if(bucketList.size()>0) {
				for(Bucket item:bucketList) {
					query = "select price from items where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
		
	}
}
