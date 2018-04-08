package service;

import java.util.List;

import dao.RecordDao;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

public class SpendService {
	public SpendPage getSpendPage() {
		RecordDao dao = new RecordDao();
		List<Record> thisMonthRecords = dao.listThisMonth();
		List<Record> todayRecords = dao.listTody();
		int thisMonthTotalDay = DateUtil.totalDayofMonth();
		
		int monthSpend = 0;
		int todaySpend= 0;
		int avgSpendPerDay = 0;
		int monthLeft = 0 ;
		int dayAvgAvaible = 0 ;
		int monthLeftDay = 0;
		int usagePercentage = 0;
		
		int monthBudget = new ConfigService().getIntBudget();
		
		for (Record record : thisMonthRecords) {
			monthSpend += record.spend;
		}
		
		for (Record record : todayRecords) {
			todaySpend += record.spend;
		}
		monthLeftDay = DateUtil.leftDayThisMonth();
		avgSpendPerDay = monthSpend / (thisMonthTotalDay - monthLeftDay);
		monthLeft = monthBudget - monthSpend;
		dayAvgAvaible = monthLeft / monthLeftDay;
		usagePercentage = monthSpend * 100 /monthBudget;
		return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthLeft, dayAvgAvaible, monthLeftDay, usagePercentage);
		
	}
}
