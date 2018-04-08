package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

public class RecordDao {
	public int getTotal() {
		int total = 0;
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "select count(*) from record";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public void add(Record record) {
		String sql = "insert into record value(null, ?, ?, ?, ?)";
		int id = 0;
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);) {
			ps.setInt(2, record.cid);
			ps.setInt(1, record.spend);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next())
				id = rs.getInt(1);
			record.id = id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Record record) {
		String sql = "update record	set spend = ?, cid = ?, comment = ?, date = ? where id = ?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);) {
			ps.setInt(2, record.cid);
			ps.setInt(1, record.spend);
			ps.setString(2, record.comment);
			ps.setDate(3, DateUtil.util2sql(record.date));
			ps.setInt(4, record.id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "delete from record where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Record get(int id) {
		Record record = null;
		try (Statement s = DBUtil.getConnection().createStatement();) {
			String sql = "select * from record where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				record = new Record();
				record.cid = rs.getInt("cid");
				record.spend = rs.getInt("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	public List<Record> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Record> list(int cid) {
		List<Record> records = new ArrayList<>();
		Record record = null;
		String sql = "select * from record where cid = ?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);) {
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				record = new Record();
				record.cid = rs.getInt("cid");
				record.spend = rs.getInt("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> list(int start, int count) {
		List<Record> records = new ArrayList<>();
		Record record = null;
		String sql = "select * from record order by id desc limit ?,?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);) {
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				record = new Record();
				record.cid = rs.getInt("cid");
				record.spend = rs.getInt("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Record> list(Date start, Date count) {
		List<Record> records = new ArrayList<>();
		Record record = null;
		String sql = "select * from record where date >= ? and date <= ?";
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql, 1);) {
			ps.setDate(1, DateUtil.util2sql(start));
			ps.setDate(2, DateUtil.util2sql(count));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				record = new Record();
				record.cid = rs.getInt("cid");
				record.spend = rs.getInt("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Record> list(Date day) {
		return list(DateUtil.util2sql(day), DateUtil.util2sql(day));
	}

	public List<Record> listThisMonth() {
		return list(DateUtil.util2sql(DateUtil.monthBegin()), DateUtil.util2sql(DateUtil.monthEnd()));
	}

	public List<Record> listTody() {
		return list(DateUtil.util2sql(DateUtil.today()), DateUtil.util2sql(DateUtil.today()));
	}
}
