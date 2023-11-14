package model.service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){		
		return dao.findAll();
	}
	
	public void saveOrUptade(Department department) {
		if(department.getId() == null) {
			dao.insert(department);
		}else {
			dao.update(department);
		}
	}
}
