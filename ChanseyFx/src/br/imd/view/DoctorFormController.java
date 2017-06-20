package br.imd.view;

import java.util.List;

import br.imd.dao.DoctorDao;
import br.imd.dao.SectorDao;
import br.imd.dao.SpecialtyDao;
import br.imd.model.Doctor;
import br.imd.model.Sector;
import br.imd.model.Specialty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DoctorFormController extends GenericController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField crmNumberField;
	@FXML
	private TextField phoneField;
	@FXML
	private ComboBox<Specialty> specialtyField;
	@FXML
	private ComboBox<Sector> sectorField;

	private Doctor doctor = new Doctor();
	private SpecialtyDao specialtyDao = new SpecialtyDao();
	private SectorDao sectorDao = new SectorDao();

	private DoctorDao dao = new DoctorDao();

	@FXML
	private void initialize() {
		ObservableList<Sector> sectorsValues = FXCollections.observableArrayList();
		ObservableList<Specialty> specialtiesValues = FXCollections.observableArrayList();

		List<Sector> sectors = sectorDao.list();
		List<Specialty> specialties = specialtyDao.list();

		for (Sector sector : sectors) {
			sectorsValues.add(sector);
		}
		for (Specialty specialty : specialties) {
			specialtiesValues.add(specialty);
		}

		sectorField.setItems(sectorsValues);
		specialtyField.setItems(specialtiesValues);

	}

	@FXML
	public void handleSaveDoctor() {
		if (isValid()) {
			doctor.setName(nameField.getText());
			doctor.setCrmNumber(crmNumberField.getText());
			doctor.setPhone(phoneField.getText());
			dao.save(doctor);
			handleCancel();
		}
	}

	public void handleLoadDoctor(Doctor doctor) {
		this.doctor = doctor;
		nameField.setText(this.doctor.getName());
		crmNumberField.setText(this.doctor.getCrmNumber());
		phoneField.setText(this.doctor.getPhone());
		for (Specialty specialty : doctor.getSpecialties()) {
			specialtyField.getItems().remove(specialty);
		}
		for (Sector sector : doctor.getSectors()) {
			sectorField.getItems().remove(sector);
		}
	}

	@FXML
	public void handleCancel() {
		doctor = new Doctor();
		nameField.setText("");
		crmNumberField.setText("");
		phoneField.setText("");
		sectorField.setValue(null);
		specialtyField.setValue(null);
	}

	@FXML
	public void handleBack() {
		mainApp.showView("view/DoctorList.fxml", this);
	}

	@FXML
	public void handleAddSector() {
		doctor.getSectors().add(sectorField.getValue());
		sectorField.getItems().remove(sectorField.getValue());
	}

	@FXML
	public void handleAddSpecialty() {
		doctor.getSpecialties().add(specialtyField.getValue());
		specialtyField.getItems().remove(specialtyField.getValue());
	}

	private boolean isValid() {
		String text = "";
		boolean valid = true;
		Alert alert = new Alert(AlertType.WARNING);

		if (nameField.getText().equals("") || nameField == null) {
			text += "Campo Nome não pode ser vazio\n";
			valid = false;
		}
		if (phoneField.getText().equals("") || phoneField == null) {
			text += "Campo Telefone não pode ser vazio\n";
			valid = false;
		}
		if (crmNumberField.getText().equals("") || crmNumberField == null) {
			text += "Campo CRM não pode ser vazio";
			valid = false;
		}
		if (!valid) {
			alert.setTitle("Campos não preenchidos");
			alert.setHeaderText(text);
			alert.showAndWait();
		}

		return valid;
	}
}
