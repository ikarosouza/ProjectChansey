package br.imd.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.imd.dao.PatientDao;
import br.imd.model.Address;
import br.imd.model.BloodEnum;
import br.imd.model.Patient;
import br.imd.model.SexEnum;
import br.imd.model.StatesEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientFormController extends GenericController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField cpfField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emergencyNumberField;
	@FXML
	private TextField rgField;
	@FXML
	private ComboBox<String> sexField;
	@FXML
	private ComboBox<String> bloodTypeField;
	@FXML
	private TextField heightField;
	@FXML
	private TextField weightField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField houseNumberField;
	@FXML
	private TextField complementField;
	@FXML
	private TextField neighborhoodField;
	@FXML
	private TextField cityField;
	@FXML
	private ComboBox<String> stateField;
	@FXML
	private TextField zipcodeField;

	private Patient patient = new Patient();

	private PatientDao dao = new PatientDao();

	@FXML
	private void initialize() {
		ObservableList<String> sexValues = FXCollections.observableArrayList();
		ObservableList<String> bloodValues = FXCollections.observableArrayList();
		ObservableList<String> stateValues = FXCollections.observableArrayList();

		List<SexEnum> sex = new ArrayList<SexEnum>(Arrays.asList(SexEnum.values()));
		List<BloodEnum> blood = new ArrayList<BloodEnum>(Arrays.asList(BloodEnum.values()));
		List<StatesEnum> state = new ArrayList<StatesEnum>(Arrays.asList(StatesEnum.values()));

		for (SexEnum sexValue : sex) {
			sexValues.add(sexValue.getValue());
		}
		for (BloodEnum bloodValue : blood) {
			bloodValues.add(bloodValue.getValue());
		}
		for (StatesEnum stateValue : state) {
			stateValues.add(stateValue.getValue());
		}

		sexField.setItems(sexValues);
		bloodTypeField.setItems(bloodValues);
		stateField.setItems(stateValues);
		heightField.setText("0");
		weightField.setText("0");
		houseNumberField.setText("0");
	}

	@FXML
	public void handleSavePatient() {
		if (isValid()) {
			patient.setName(nameField.getText());
			patient.setRg(rgField.getText());
			patient.setCpf(cpfField.getText());
			patient.setPhone(phoneField.getText());
			patient.setEmergencyNumber(emergencyNumberField.getText());
			patient.setBloodType(bloodTypeField.getValue());
			patient.setSex(sexField.getValue());
			patient.setHeight(Double.parseDouble(heightField.getText()));
			patient.setWeight(Double.parseDouble(weightField.getText()));
			Address address = new Address();
			address.setStreet(streetField.getText());
			address.setHouseNumber(Integer.parseInt(houseNumberField.getText()));
			address.setComplement(complementField.getText());
			address.setNeighborhood(neighborhoodField.getText());
			address.setCity(cityField.getText());
			address.setState(stateField.getValue());
			address.setZipCode(zipcodeField.getText());
			patient.setAddress(address);
			dao.save(patient);
			handleCancel();
		}
	}

	public void handleLoadPatient(Patient patient) {
		this.patient = patient;
		nameField.setText(this.patient.getName());
		rgField.setText(this.patient.getRg());
		cpfField.setText(this.patient.getCpf());
		phoneField.setText(this.patient.getPhone());
		emergencyNumberField.setText(this.patient.getEmergencyNumber());
		bloodTypeField.setValue(this.patient.getBloodType());
		sexField.setValue(this.patient.getSex());
		heightField.setText(this.patient.getHeight() + "");
		weightField.setText(this.patient.getWeight() + "");
		streetField.setText(this.patient.getAddress().getStreet());
		houseNumberField.setText(this.patient.getAddress().getHouseNumber() + "");
		complementField.setText(this.patient.getAddress().getComplement());
		neighborhoodField.setText(this.patient.getAddress().getNeighborhood());
		cityField.setText(this.patient.getAddress().getCity());
		stateField.setValue(this.patient.getAddress().getState());
		zipcodeField.setText(this.patient.getAddress().getZipCode());
	}

	@FXML
	public void handleCancel() {
		patient = new Patient();
		nameField.setText("");
		rgField.setText("");
		cpfField.setText("");
		phoneField.setText("");
		emergencyNumberField.setText("");
		bloodTypeField.setValue("");
		sexField.setValue("");
		heightField.setText("");
		weightField.setText("");
		streetField.setText("");
		houseNumberField.setText("");
		complementField.setText("");
		neighborhoodField.setText("");
		cityField.setText("");
		stateField.setValue("");
		zipcodeField.setText("");
	}

	@FXML
	public void handleBack() {
		mainApp.showView("view/PatientList.fxml", this);
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
		if ((rgField.getText().equals("") || rgField == null) || ((rgField.getText().equals("") || rgField == null))) {
			text += "Preencha o RG ou CPF\n";
			valid = false;
		}
		if (sexField.getValue() == null) {
			text += "Selecione o sexo do paciente\n";
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
