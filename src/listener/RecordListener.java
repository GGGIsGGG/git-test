package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import service.RecordService;
import util.GUIUtil;

public class RecordListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		RecordPanel p = RecordPanel.instance;
		RecordService s = new RecordService();
		if(0 == p.cbModel.getSize()) {
			JOptionPane.showInputDialog(p, "暂无消费分类，无法添加消费，请先增加消费分类");
			MainPanel.instance.workingPanel.show(CategoryPanel.instance);
		}
		if(!GUIUtil.cheakZero(p.tfSpend, "花费（￥）"))
			return;
		s.add(Integer.parseInt(p.tfSpend.getText().trim()), (Category)p.cbModel.getSelectedItem(), p.tfComment.getText(),
				p.datePicker.getDate());
		JOptionPane.showMessageDialog(p, "添加成功！");
		p.tfComment.setText("");
		p.tfSpend.setText("");
		p.tfSpend.grabFocus();
	}
	
}
