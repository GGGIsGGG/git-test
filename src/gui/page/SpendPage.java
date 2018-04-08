package gui.page;

public class SpendPage {
	public String MonthSpend;
	public String TodaySpend;
	public String AvgSpendPerDay;
	public String MonthLeft ;
	public String DayAvgAvaible ;
	public String MonthLeftDay;
	public int usagePercentage;
	public boolean isOverSpend = false;
	
	public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthLeft, int dayAvgAvaible,
			int monthLeftDay, int usagePercentage) {
		MonthSpend = "гд" + monthSpend;
		TodaySpend = "гд" + todaySpend;
		AvgSpendPerDay = "гд" + avgSpendPerDay;
		if(monthLeft < 0)
			isOverSpend = true;
		if(!isOverSpend) {
			MonthLeft = "гд" + monthLeft;
			DayAvgAvaible = "гд" + dayAvgAvaible;
		}else {
			MonthLeft = "│м╓з" + -monthLeft;
			DayAvgAvaible = "гд0";
		}
		this.MonthLeftDay = monthLeftDay + "╠ь";
		this.usagePercentage = usagePercentage;
	}
}
