package gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listener.ConfigListenr;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

public class ConfigPanel extends WorkingPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5064218541340700395L;
	public static ConfigPanel instance = new ConfigPanel();
	JLabel JBudget = new JLabel("本月预算");
	JLabel JMySQL = new JLabel("MySQL安装目录");
	
	public JTextField tfBudget = new JTextField();
	public JTextField tfMySQL = new JTextField();
	
	public JButton bUpdate = new JButton("更新");
	private ConfigPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bUpdate);
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		north.setLayout(new GridLayout(4, 1, 40, 40));
		north.add(JBudget);
		north.add(tfBudget);
		north.add(JMySQL);
		north.add(tfMySQL);
		center.add(bUpdate);
		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		updateData();
		addListener();
	}
	
	public void addListener() {
		ConfigListenr listener = new ConfigListenr();
		bUpdate.addActionListener(listener);
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(instance);
	}

	@Override
	public void updateData() {
		String budget = new ConfigService().get(ConfigService.budget);
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		tfBudget.setText(budget);
		tfMySQL.setText(mysqlPath);
		tfMySQL.grabFocus();
		this.updateUI();
	}
}
