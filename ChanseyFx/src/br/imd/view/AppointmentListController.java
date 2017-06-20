package br.imd.view;

import java.util.Date;
import java.util.List;

import br.imd.MainApp;
import br.imd.dao.AppointmentDao;
import br.imd.model.Appointment;
import br.imd.util.DateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AppointmentListController extends GenericController {
	@FXML
	private TableView<Appointment> table;
	@FXML
	private TableColumn<Appointment, String> patientColumn;
	@FXML
	private TableColumn<Appointment, String> doctorColumn;
	@FXML
	private TableColumn<Appointment, String> dateColumn;
	@FXML
	private TextField patientSearchField;
	@FXML
	private TextField DoctorSearchField;
	@FXML
	private DatePicker dateSearchField;
	
	
	private AppointmentDao dao = new AppointmentDao();
	
	private MainApp mainApp;
	
	private AppointmentFormController formController;

	public AppointmentListController() {
		
	}
	
	@FXML
	private void initialize(){
		patientColumn.setCellValueFactory(cellData -> cellData.getValue().getPatient().name());
		doctorColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctor().name());
		dateColumn.setCellValueFactory(cellData -> 
		new SimpleStringProperty(DateUtil.format(cellData.getValue().getDate())));
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		handleFillTable();
	}
	
	@FXML
	public void openAppointmentForm(){
		mainApp.showView("view/AppointmentForm.fxml", this);
	}
	
	@FXML
	public void handleEditAppointment(){
		Alert alert;
		
		Appointment appointment = table.getSelectionModel().getSelectedItem();
		if(appointment != null){
			mainApp.showView("view/AppointmentForm.fxml", this);
			formController.handleLoadAppointment(appointment);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhuma consulta selecionada");
			alert.setHeaderText("Selecione uma consulta para editar");
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void handleDeleteAppointment(){
		Alert alert;
		Appointment appointment = table.getSelectionModel().getSelectedItem();
		if(appointment != null){
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Tem certeza que deseja deletar essa consulta?");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK){
				table.getItems().remove(appointment);
				dao.remove(appointment);
			}
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhuma consulta selecionada");
			alert.setHeaderText("Selecione uma consulta para deletar");
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void handleSearch(){
		Date searchDate = null;
		if(dateSearchField.getValue() != null){
			searchDate = java.sql.Date.valueOf(dateSearchField.getValue());
		}
		List<Appointment> appointments = dao.search(patientSearchField.getText(), DoctorSearchField.getText(), searchDate);
		ObservableList<Appointment> appointmentsResult = FXCollections.observableArrayList();
		for(Appointment appointment : appointments){
			appointmentsResult.add(appointment);
		}
		table.getItems().clear();
		table.setItems(appointmentsResult);
	}
	
	@FXML 
	public void handleFillTable(){
		patientSearchField.setText("");
		DoctorSearchField.setText("");
		dateSearchField.setValue(null);
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		for (Appointment appointment : dao.list()) {
			appointments.add(appointment);
		}
		table.getItems().clear();
		table.setItems(appointments);
	}
	
	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if(otherViewController instanceof AppointmentFormController){
			this.formController = (AppointmentFormController) otherViewController;
		}	
	}
}
