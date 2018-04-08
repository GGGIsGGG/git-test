package gui.frame;

import javax.swing.JFrame;

import gui.panel.MainPanel;
import util.GUIUtil;


public class MainFrame extends JFrame{
	public static MainFrame instance = new MainFrame();
	private MainFrame() {
		this.setTitle("Ò»±¾ºýÍ¿ÕË");
		this.setSize(500, 450);
		this.setLocationRelativeTo(null);
		this.setContentPane(MainPanel.instance);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		instance.setVisible(true);
	}
}
