package gui.panel;

import javax.swing.JButton;
import listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

public class RecoverPanel extends WorkingPanel{
	public static RecoverPanel instance = new RecoverPanel();
	public JButton JBackup = new JButton("»Ö¸´");
	private RecoverPanel() {
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
		RecoverListener l = new RecoverListener();
		JBackup.addActionListener(l);
		
	}
}
