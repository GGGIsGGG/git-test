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
		MonthSpend = "��" + monthSpend;
		TodaySpend = "��" + todaySpend;
		AvgSpendPerDay = "��" + avgSpendPerDay;
		if(monthLeft < 0)
			isOverSpend = true;
		if(!isOverSpend) {
			MonthLeft = "��" + monthLeft;
			DayAvgAvaible = "��" + dayAvgAvaible;
		}else {
			MonthLeft = "��֧" + -monthLeft;
			DayAvgAvaible = "��0";
		}
		this.MonthLeftDay = monthLeftDay + "��";
		this.usagePercentage = usagePercentage;
	}
}
