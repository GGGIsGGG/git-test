package gui.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

public class SpendPanel extends WorkingPanel{
//	static {
//		GUIUtil.useLNF();
//	}
	public static SpendPanel instance = new SpendPanel();
	public JLabel lMonthSpend = new JLabel("本月消费");
	public JLabel lTodaySpend = new JLabel("今日消费");
	public JLabel lAvgSpendPerDay = new JLabel("日均消费");
	public JLabel lMonthLeft = new JLabel("本月剩余");
	public JLabel lDayAvgAvaible = new JLabel("日均可用");
	public JLabel lMonthLeftDay = new JLabel("距离月末");
	
	public JLabel vMonthSpend = new JLabel("￥3000");
	public JLabel vTodaySpend = new JLabel("￥56");
	public JLabel vAvgSpendPerDay = new JLabel("￥100");
	public JLabel vMonthLeft = new JLabel("￥2215");
	public JLabel vDayAvgAvaible = new JLabel("￥355");
	public JLabel vMonthLeftDay = new JLabel("5天");
	
	public CircleProgressBar cir = new CircleProgressBar();
	
	private SpendPanel() {
		this.setLayout(new BorderLayout());
		cir.setBackgroundColor(ColorUtil.blueColor);
		cir.setProgress(0);
		cir.setForegroundColor(ColorUtil.getByPercentage(0));
		GUIUtil.setColor(ColorUtil.grayColor, lAvgSpendPerDay, lMonthLeft, lDayAvgAvaible, lMonthLeftDay, lMonthSpend,
				lTodaySpend, vAvgSpendPerDay, vMonthLeft, vDayAvgAvaible, vMonthLeftDay);
		GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
		vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
		vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
		this.add(center(), BorderLayout.CENTER);
		this.add(south(), BorderLayout.SOUTH);
	};
	
	public JPanel center() {
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		center.add(cir, BorderLayout.CENTER);
		center.add(west(), BorderLayout.WEST);
		return center;
	}
	public JPanel west() {
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(4, 1));
		west.add(lMonthSpend);
		west.add(vMonthSpend);
		west.add(lTodaySpend);
		west.add(vTodaySpend);
		return west;
	}
	public JPanel south() {
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(2, 4));
		south.add(lAvgSpendPerDay);
		south.add(lMonthLeft);
		south.add(lDayAvgAvaible);
		south.add(lMonthLeftDay);
		south.add(vAvgSpendPerDay);
		south.add(vMonthLeft);
		south.add(vDayAvgAvaible);
		south.add(vMonthLeftDay);
		return south;
	}
			
	public static void main(String[] args) {
		GUIUtil.showPanel(instance);
	}

	@Override
	public void updateData() {
		SpendPage spend = new SpendService().getSpendPage();
		vMonthSpend.setText(spend.MonthSpend);
		vAvgSpendPerDay.setText(spend.AvgSpendPerDay);
		vDayAvgAvaible.setText(spend.DayAvgAvaible);
		vMonthLeft.setText(spend.MonthLeft);
		vMonthLeftDay.setText(spend.MonthLeftDay);
		vTodaySpend.setText(spend.TodaySpend);
		
		cir.setProgress(spend.usagePercentage);
		if(spend.isOverSpend) {
			vMonthSpend.setForeground(ColorUtil.warningColor);
			vMonthLeft.setForeground(ColorUtil.warningColor);
			vTodaySpend.setForeground(ColorUtil.warningColor);
		}else {
			vMonthSpend.setForeground(ColorUtil.blueColor);
			vMonthLeft.setForeground(ColorUtil.grayColor);
			vTodaySpend.setForeground(ColorUtil.blueColor);
		}
		cir.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
//		cir.setForeground(ColorUtil.blueColor);
		cir.updateUI();
		addListener();
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
}

//JLabel lMonthSpend = new JLabel("本月消费");
//	JLabel lTodaySpend = new JLabel("今日消费");
//	JLabel lAvgSpendPerDay = new JLabel("日均消费");
//	JLabel lMonthLeft = new JLabel("本月剩余");
//	JLabel lDayAvgAvaible = new JLabel("日均可用");
//	JLabel lMonthLeftDay = new JLabel("距离月末");
//	
//	JLabel vMonthSpend = new JLabel("￥3000");
//	JLabel vTodaySpend = new JLabel("￥56");
//	JLabel vAvgSpendPerDay = new JLabel("￥100");
//	JLabel vMonthLeft = new JLabel("￥2215");
//	JLabel vDayAvgAvaible = new JLabel("￥355");
//	JLabel vMonthLeftDay = new JLabel("5天");
	