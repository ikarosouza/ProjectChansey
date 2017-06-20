package br.imd.view;

import br.imd.dao.DoctorDao;
import br.imd.model.Doctor;
import br.imd.model.Sector;
import br.imd.model.Specialty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class DoctorOverviewController extends GenericController {
	@FXML
	private Label nameLabel;
	@FXML
	private Label crmNumberLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label specialtiesLabel;
	@FXML
	private Label sectorsLabel;

	private Doctor doctor;

	private DoctorFormController formController;

	private DoctorDao dao = new DoctorDao();

	@FXML
	private void initialize() {
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
		String specialties = "";
		String sectors = "";
		nameLabel.setText(this.doctor.getName());
		crmNumberLabel.setText(this.doctor.getCrmNumber());
		phoneLabel.setText(this.doctor.getPhone());
		for (Specialty specialty : doctor.getSpecialties()) {
			specialties += specialty.getName() + ", ";
		}
		for (Sector sector : doctor.getSectors()) {
			sectors += sector.getName() + ", ";
		}
		specialties = specialties.trim();
		specialties = specialties.substring(0, specialties.length() - 1);
		sectors = sectors.trim();
		sectors = sectors.substring(0, sectors.length() - 1);
		specialtiesLabel.setText(specialties);
		sectorsLabel.setText(sectors);
	}

	@FXML
	public void handleBack() {
		mainApp.showView("view/DoctorList.fxml", this);
	}

	@FXML
	public void handleEdit() {
		mainApp.showView("view/DoctorForm.fxml", this);
		formController.handleLoadDoctor(doctor);
	}

	@FXML
	public void handleDelete() {
		Alert alert;
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Tem certeza que deseja deletar esse médico?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			dao.remove(doctor);
			mainApp.showView("view/DoctorList.fxml", this);
		}		
	}

	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if (otherViewController instanceof DoctorFormController) {
			this.formController = (DoctorFormController) otherViewController;
		}
	}
}
