package gui.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import entity.Record;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

public class ReportPanel extends WorkingPanel{
	public static ReportPanel instance = new ReportPanel();
	JLabel l = new JLabel();
	private ReportPanel() {
		this.setLayout(new BorderLayout());
		List<Record> rs = new ReportService().listThisMonthRecords();
		l.setIcon(new ImageIcon(ChartUtil.getImage(rs, 400, 300)));
		this.add(l);
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(instance);
	}
	@Override
	public void updateData() {
		List<Record> rs = new ReportService().listThisMonthRecords();
		l.setIcon(new ImageIcon(ChartUtil.getImage(rs,350, 250)));	
//		l.updateUI();
	}
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
}
