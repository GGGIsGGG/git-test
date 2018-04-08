package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Config;
import util.DBUtil;

public class ConfigDao {
	public int getTotal() {
		int total = 0;
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "select count(*) from config";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Config config) {
		String sql = "insert into config value(null, ?, ?)";
		int id = 0;
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);){
			ps.setString(1, config.key);
			ps.setString(2, config.value);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next())
				id = rs.getInt(1);
			config.id = id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Config config) {
		String sql = "update config	set key_= ?, value = ? where id = ?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);){
			ps.setString(1, config.key);
			ps.setString(2, config.value);
			ps.setInt(3, config.id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "delete from config where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Config get(int id) {
		Config config = null;
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "select * from config where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				config = new Config();
				config.id = id;
				config.key = rs.getString("key_");
				config.value = rs.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public Config getByKey(String key) {
		Config config = null;
		String sql = "select * from config where key_ = ?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);) {
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				config = new Config();
				config.id = rs.getInt("id");
				config.key = rs.getString("key_");
				config.value = rs.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public List<Config> list(){
		return list(0, Short.MAX_VALUE);
	}
	public List<Config> list(int start, int count){
		List<Config> configs = new ArrayList<>();
		Config config = null;
		String sql = "select * from config order by id desc limit ?,?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				config = new Config();
				config.id = rs.getInt("id");
				config.key = rs.getString("key_");
				config.value = rs.getString("value");
				configs.add(config);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return configs;
	}
}
