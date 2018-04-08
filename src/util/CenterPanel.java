package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3917917810833075352L;
	private double rate;
	private JComponent c;
	private boolean strech;
	
	public CenterPanel(double rate, boolean strech) {
		this.setLayout(null);
		this.rate = rate;
		this.strech = strech;
	}
	
	public CenterPanel(double rate) {
		this(rate, true);
	}
	
	@Override
	public void repaint() {
		if(null != c) {
			Dimension containerSize = this.getSize();
			Dimension componentSize = c.getPreferredSize();
			if(strech)
				c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
			else
				c.setSize(componentSize);
			c.setLocation((containerSize.width - c.getSize().width) / 2, (containerSize.height - c.getSize().height) / 2);
		}
			
		super.repaint();
	}
	public void show(JComponent p) {
		this.c = p;
		Component[] cs = getComponents();
		for (Component c : cs) {
			remove(c);
		}
		add(p);
		if(p instanceof WorkingPanel)
			((WorkingPanel)p).updateData();
		this.updateUI();
	}
	
}
