package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.GUIUtil;
import util.Mysqlutil;

public class RecoverListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		BackupPanel p = BackupPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		if(0 == mysqlPath.length()) {
			JOptionPane.showMessageDialog(p, "���������ú�mysql��·��");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMySQL.grabFocus();
		}
		
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("hutubill.sql"));
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".sql";
			}
			
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".sql");
			}
		});
		
		int returnVal = fc.showSaveDialog(p);
		File file = fc.getSelectedFile();
		System.out.println(file);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println(file);
			if(!file.getName().toLowerCase().endsWith(".sql"))
				file = new File(file.getParent(), file.getName() + ".sql");
			System.out.println(file);
		}
		
		try {
			Mysqlutil.recover(mysqlPath, file.getAbsolutePath());
			JOptionPane.showMessageDialog(p, "�ָ��ɹ���");
		}catch(IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(p, "�ָ�ʧ�ܣ�\r\n"+ e1.getMessage());
		}
		
	}

}

