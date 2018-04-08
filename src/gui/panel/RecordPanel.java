package gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import gui.model.CategoryComboBoxModel;
import listener.RecordListener;
import service.CategoryService;
import util.CenterPanel;
import util.ColorUtil;
import util.GUIUtil;

@SuppressWarnings("unchecked")
public class RecordPanel extends WorkingPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6150249334358920340L;
	public static RecordPanel instance = new RecordPanel();
	JLabel lSpend = new JLabel("花费（￥）");
	JLabel lCategory = new JLabel("分类");
	JLabel lComment = new JLabel("备注");
	JLabel lDate = new JLabel("日期");
	
	public JTextField tfSpend = new JTextField();
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<String> cbCategory = new JComboBox<>(cbModel);
	public JTextField tfComment = new JTextField();
	public JXDatePicker datePicker = new JXDatePicker(new Date());
	
	JButton bSubmit = new JButton("记一笔");
	private RecordPanel() {
		this.setLayout(new BorderLayout());
		GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
		this.add(north(), BorderLayout.NORTH);
		this.add(center(), BorderLayout.CENTER);
		addListener();
	}
	
	JPanel north() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 2, 40, 40));
		p.add(lSpend);
		p.add(tfSpend);
		p.add(lCategory);
		p.add(cbCategory);
		p.add(lComment);
		p.add(tfComment);
		p.add(lDate);
		p.add(datePicker);
		return p;
		
	}
	JPanel center() {
		JPanel p = new JPanel();
		p.add(bSubmit);
		return p;
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(instance);
	}

	@Override
	public void updateData() {
		cbModel.cs = new CategoryService().list();
		cbCategory.updateUI();
		tfComment.setText("");
		tfSpend.setText("");
		tfSpend.grabFocus();
		datePicker.setDate(new Date());
		
	}

	@Override
	public void addListener() {
		RecordListener l = new RecordListener();
		bSubmit.addActionListener(l);
		
	}
}
