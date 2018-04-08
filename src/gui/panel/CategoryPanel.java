package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.Category;
import gui.model.CategoryTableModel;
import listener.CategoryListener;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel{
	public static CategoryPanel instance = new CategoryPanel();
	public JButton bAdd = new JButton("ÐÂÔö");
	public JButton bEdit = new JButton("±à¼­");
	public JButton bDel = new JButton("É¾³ý");
	
	
	public CategoryTableModel ctm = new CategoryTableModel();
	public JTable t = new JTable(ctm);

	private CategoryPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDel);
		this.setLayout(new BorderLayout());
		this.add(center(), BorderLayout.CENTER);
		this.add(south(), BorderLayout.SOUTH);
		addListener();
	}
	
	JScrollPane center() {
		JScrollPane sp = new JScrollPane(t);
		return sp;
	}
	JPanel south() {
		JPanel p = new JPanel();
		p.add(bAdd);
		p.add(bEdit);
		p.add(bDel);
		return p;
	}
	
	public Category getSelectdCategory() {
		int index = t.getSelectedRow();
		return ctm.cs.get(index);
	}
	
	public void updateData() {
		ctm.cs = new CategoryService().list();
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0, 0);
		
		if(0 == ctm.cs.size()) {
			bEdit.setEnabled(false);
			bDel.setEnabled(false);
		}else {
			bEdit.setEnabled(true);
			bDel.setEnabled(true);
		}
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(CategoryPanel.instance);
	}
	
	public void addListener() {
		CategoryListener l = new CategoryListener();
		bAdd.addActionListener(l);
		bDel.addActionListener(l);
		bEdit.addActionListener(l);
	}
}
