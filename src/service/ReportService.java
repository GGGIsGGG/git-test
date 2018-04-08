package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDao;
import entity.Record;
import util.DateUtil;

public class ReportService {
	public int getDaySpend(Date d, List<Record> monthRawData) {
		int daySpend = 0;
		for (Record record : monthRawData) {
			if(record.date.equals(d))
				daySpend += record.spend;
		}
		return daySpend;
	}
	
	public List<Record> listThisMonthRecords(){
		RecordDao dao = new RecordDao();
		List<Record> monthRawData = dao.listThisMonth();
		List<Record> result = new ArrayList<>();
		Date monthBegin = DateUtil.monthBegin();
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < DateUtil.totalDayofMonth(); i++) {
			Record r = new Record();
			c.setTime(monthBegin);
			c.add(Calendar.DATE, i);
			Date eachDayOfthisMonth = c.getTime();
			int daySpend = getDaySpend(eachDayOfthisMonth, monthRawData);
			r.spend = daySpend;
			result.add(r);
		}
		return result;
	}
}
