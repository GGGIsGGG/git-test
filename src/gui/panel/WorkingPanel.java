package gui.panel;

import javax.swing.JPanel;

public abstract class WorkingPanel extends JPanel {

	private static final long serialVersionUID = -3746285191732631669L;
	public abstract void updateData();
	public abstract void addListener();
}
