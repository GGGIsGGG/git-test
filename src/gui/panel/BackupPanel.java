package gui.panel;

import javax.swing.JButton;
import listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

public class BackupPanel extends WorkingPanel{
	public static BackupPanel instance = new BackupPanel();
	public JButton JBackup = new JButton("±¸·Ý");
	private BackupPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, JBackup);
		this.add(JBackup);
		addListener();
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(instance);
	}
	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addListener() {
		BackupListener l = new BackupListener();
		JBackup.addActionListener(l);
		
	}
}
