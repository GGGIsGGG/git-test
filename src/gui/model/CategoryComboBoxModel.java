package gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import entity.Category;
import service.CategoryService;

@SuppressWarnings("rawtypes")
public class CategoryComboBoxModel implements ComboBoxModel{
	public List<Category> cs = new CategoryService().list();
	Category c = null;
	public CategoryComboBoxModel() {
		if(!cs.isEmpty())
			c = cs.get(0);
	}
	@Override
	public int getSize() {
		return cs.size();
	}

	@Override
	public Object getElementAt(int index) {
		return cs.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		c = (Category) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return c;
	}
	
}
