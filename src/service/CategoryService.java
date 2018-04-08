package service;

import java.util.Collections;
import java.util.List;

import dao.CategoryDao;
import dao.RecordDao;
import entity.Category;
import entity.Record;

public class CategoryService {
	CategoryDao cDao = new CategoryDao();
	RecordDao rDao = new RecordDao();
	public List<Category> list(){
		List<Category> cs = cDao.list();
		for (Category category : cs) {
			List<Record> rs = rDao.list(category.id);
			category.setRecordNumber(rs.size());
		}
		Collections.sort(cs, (c1, c2)-> c2.recordNumber-c1.recordNumber);
		return cs;
	}
	
	public void add(String name) {
		Category c =  new Category();
		c.name = name;
		cDao.add(c);
	}
	
	public void update(int id, String name) {
		Category c =  new Category();
		c.name = name;
		c.id = id;
		cDao.update(c);
	}
	
	public void delete(int id ) {
		cDao.delete(id);
	}
}

