package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DBUtil;

public class CategoryDao {
	public int getTotal() {
		int total = 0;
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "select count(*) from category";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Category category) {
		String sql = "insert into category value(null, ?)";
		int id = 0;
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);){
			ps.setString(1, category.name);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next())
				id = rs.getInt(1);
			category.id = id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Category category) {
		String sql = "update category	set name = ? where id = ?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);){
			ps.setString(1, category.name);
			ps.setInt(2, category.id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "delete from category where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Category get(int id) {
		Category category = null;
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "select * from category where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				category = new Category();
				category.id = id;
				category.name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public List<Category> list(){
		return list(0, Short.MAX_VALUE);
	}
	
	public List<Category> list(int start, int count){
		List<Category> categorys = new ArrayList<>();
		Category category = null;
		String sql = "select * from category order by id desc limit ?,?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				category = new Category();
				category.id = rs.getInt("id");
				category.name = rs.getString("name");
				categorys.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorys;
	}
}