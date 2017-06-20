package br.imd.view;

import br.imd.dao.PatientDao;
import br.imd.model.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class PatientOverviewController extends GenericController {
	@FXML
	private Label nameLabel;
	@FXML
	private Label rgLabel;
	@FXML
	private Label cpfLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label emergencyNumberLabel;
	@FXML
	private Label sexLabel;
	@FXML
	private Label bloodTypeLabel;
	@FXML
	private Label heightLabel;
	@FXML
	private Label weightLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label houseNumberLabel;
	@FXML
	private Label neighborhoodLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label stateLabel;
	@FXML
	private Label zipcodeLabel;
	@FXML
	private Label complementLabel;
	
	private Patient patient;
	
	private PatientFormController formController;
	
	private PatientDao dao = new PatientDao();
	
	@FXML
	private void initialize(){
	}
	
	public void setPatient(Patient patient){
		this.patient = patient;
		nameLabel.setText(this.patient.getName());
		rgLabel.setText(this.patient.getRg());
		cpfLabel.setText(this.patient.getCpf());
		phoneLabel.setText(this.patient.getPhone());
		emergencyNumberLabel.setText(this.patient.getEmergencyNumber());
		bloodTypeLabel.setText(this.patient.getBloodType());
		sexLabel.setText(this.patient.getSex());
		heightLabel.setText(this.patient.getHeight() + "");
		weightLabel.setText(this.patient.getWeight() + "");
		streetLabel.setText(this.patient.getAddress().getStreet());
		houseNumberLabel.setText(this.patient.getAddress().getHouseNumber() + "");
		complementLabel.setText(this.patient.getAddress().getComplement());
		neighborhoodLabel.setText(this.patient.getAddress().getNeighborhood());
		cityLabel.setText(this.patient.getAddress().getCity());
		stateLabel.setText(this.patient.getAddress().getState());
		zipcodeLabel.setText(this.patient.getAddress().getZipCode());
	}
	
	@FXML
	public void handleBack(){
		mainApp.showView("view/PatientList.fxml", this);
	}
	
	@FXML
	public void handleEdit(){
		mainApp.showView("view/PatientForm.fxml", this);
		formController.handleLoadPatient(patient);
	}
	
	@FXML
	public void handleDelete(){		
		Alert alert;
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Tem certeza que deseja deletar esse paciente?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			dao.remove(patient);
			mainApp.showView("view/PatientList.fxml", this);
		}
	}
	
	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if(otherViewController instanceof PatientFormController){
			this.formController = (PatientFormController) otherViewController;
		}		
	}
}
