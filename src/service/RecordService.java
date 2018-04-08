package service;

import java.util.Date;

import dao.RecordDao;
import entity.Category;
import entity.Record;

public class RecordService {
	RecordDao dao = new RecordDao();
	public void add(int spend, Category c, String comment, Date date) {
		Record r = new Record();
		r.spend = spend;
		r.cid = c.id;
		r.comment = comment;
		r.date = date;
		dao.add(r);
	}
}
