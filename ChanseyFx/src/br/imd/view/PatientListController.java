package br.imd.view;

import java.util.List;

import br.imd.MainApp;
import br.imd.dao.PatientDao;
import br.imd.model.Doctor;
import br.imd.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientListController extends GenericController{
	@FXML
	private TableView<Patient> table;
	@FXML
	private TableColumn<Patient, String> nameColumn;
	@FXML
	private TableColumn<Patient, String> phoneColumn;
	@FXML
	private TableColumn<Patient, String> cpfColumn;
	@FXML
	private TableColumn<Patient, String> rgColumn;
	@FXML
	private TextField nameSearchField;
	@FXML
	private TextField phoneSearchField;
	@FXML
	private TextField cpfSearchField;
	@FXML
	private TextField rgSearchField;
	
	private PatientDao dao = new PatientDao();
	
	private MainApp mainApp;
	
	private PatientFormController formController;
	private PatientOverviewController detailsController;

	public PatientListController() {
		
	}
	
	@FXML
	private void initialize(){
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
		phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phone());
		cpfColumn.setCellValueFactory(cellData -> cellData.getValue().cpf());
		rgColumn.setCellValueFactory(cellData -> cellData.getValue().rg());
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		handleFillTable();
	}
	
	@FXML
	public void openPatientForm(){
		mainApp.showView("view/PatientForm.fxml", this);
	}
	
	@FXML
	public void handleEditPacient(){
		Alert alert;

		Patient patient = table.getSelectionModel().getSelectedItem();
		if (patient != null) {
			mainApp.showView("view/PatientForm.fxml", this);
			formController.handleLoadPatient(patient);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum paciente selecionado");
			alert.setHeaderText("Selecione um paciente para editar");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleDeletePatient(){
		Alert alert;
		Patient patient = table.getSelectionModel().getSelectedItem();
		if(patient != null){
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Tem certeza que deseja deletar esse paciente?");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK){
				table.getItems().remove(patient);
				dao.remove(patient);
			}
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum paciente selecionado");
			alert.setHeaderText("Selecione um paciente para deletar");
			alert.showAndWait();
		}	
	}
	
	@FXML
	public void handlePatientDetails(){
		Alert alert;

		Patient patient = table.getSelectionModel().getSelectedItem();
		if (patient != null) {
			mainApp.showView("view/PatientOverview.fxml", this);
			detailsController.setPatient(patient);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum paciente selecionado");
			alert.setHeaderText("Selecione um paciente");
			alert.showAndWait();
		}	
	}
	
	@FXML
	public void handleSearch(){
		List<Patient> patients = dao.search(nameSearchField.getText(), phoneSearchField.getText(), cpfSearchField.getText(), rgSearchField.getText());
		ObservableList<Patient> patientsResult = FXCollections.observableArrayList();
		for(Patient patient : patients){
			patientsResult.add(patient);
		}
		table.getItems().clear();
		table.setItems(patientsResult);
	}
	
	@FXML 
	public void handleFillTable(){
		nameSearchField.setText("");
		phoneSearchField.setText("");
		cpfSearchField.setText("");
		rgSearchField.setText("");
		ObservableList<Patient> patients = FXCollections.observableArrayList();
		for (Patient patient : dao.list()) {
			patients.add(patient);
		}
		table.getItems().clear();
		table.setItems(patients);
	}
	
	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if(otherViewController instanceof PatientFormController){
			this.formController = (PatientFormController) otherViewController;
		} else if(otherViewController instanceof PatientOverviewController){
			this.detailsController = (PatientOverviewController) otherViewController;
		}		
	}
}
