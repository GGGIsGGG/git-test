package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListenr implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.instance;
		if(!GUIUtil.cheakNumber(p.tfBudget, "����Ԥ��"))
			return;
		String mysqlPath = p.tfMySQL.getText();
		if(0 != mysqlPath.length()) {
			File commandFile = new File(mysqlPath, "bin/mysql.exe");
			if(!commandFile.exists()) {
				JOptionPane.showMessageDialog(p, "Mysql·������ȷ��");
				p.tfMySQL.grabFocus();
				return;
			}	
		}
		
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget, p.tfBudget.getText());
		cs.update(ConfigService.mysqlPath, mysqlPath);
		p.updateData();
		JOptionPane.showMessageDialog(p, "���³ɹ���");

	}

}
