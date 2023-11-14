package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.service.DepartmentService;

public class DepartmentFormController implements Initializable{

	private Department department;
	
	private DepartmentService service;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(department == null) {
			throw new IllegalStateException("Department was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			department = getFormData();
			service.saveOrUptade(department);
			Utils.currentStage(event).close();
		}catch(DbException e) {
			Alerts.showAlert("Error Saving object" , null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Department getFormData() {
		Integer id = Utils.tryParseToInt(txtId.getText());
		
		return new Department(id, txtName.getText());
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		initializeNodes();
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void uptadeFormData() {
		if(department == null) {
			throw new IllegalStateException("Entity was null.");
		}
		txtId.setText(String.valueOf(department.getId()));
		txtName.setText(department.getName());
	}

}
