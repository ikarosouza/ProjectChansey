package br.imd.view;

import java.util.HashMap;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import br.imd.dao.AppointmentDao;
import br.imd.dao.DoctorDao;
import br.imd.dao.PatientDao;
import br.imd.model.Appointment;
import br.imd.model.Doctor;
import br.imd.model.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AppointmentFormController extends GenericController{
	@FXML
	private TextField patientField;
	@FXML
	private TextField doctorField;
	@FXML
	private DatePicker dateField;

	private Appointment appointment = new Appointment();
	private PatientDao patientDao = new PatientDao();
	private DoctorDao doctorDao = new DoctorDao();

	private AppointmentDao dao = new AppointmentDao();
	
	private List<Doctor> doctors = doctorDao.list();
	private List<Patient> patients = patientDao.list();
	
	private HashMap<String, Doctor> doctorsValues = new HashMap<>();
	private HashMap<String, Patient> patientsValues = new HashMap<>();	
	
	@FXML
	private void initialize() {
		for(Doctor doctor : doctors){
			doctorsValues.put(doctor.getName(), doctor);
		}
		for(Patient patient : patients){
			patientsValues.put(patient.getName(), patient);
		}
		TextFields.bindAutoCompletion(doctorField, doctors);
		TextFields.bindAutoCompletion(patientField, patients);		
	}

	@FXML
	public void handleSaveAppointment() {
		if(isValid()){
			appointment.setPatient(patientsValues.get(patientField.getText()));
			appointment.setDoctor(doctorsValues.get(doctorField.getText()));
			appointment.setDate(java.sql.Date.valueOf(dateField.getValue()));
			dao.save(appointment);
			handleCancel();
		}		
	}

	public void handleLoadAppointment(Appointment appointment) {
		this.appointment = appointment;
		patientField.setText(this.appointment.getPatient().getName());
		doctorField.setText(this.appointment.getDoctor().getName());
		dateField.setValue(new java.sql.Date(this.appointment.getDate().getTime()).toLocalDate());
	}

	@FXML
	public void handleCancel() {
		appointment = new Appointment();
		patientField.setText("");
		doctorField.setText("");
		dateField.setValue(null);
	}

	@FXML
	public void handleBack() {
		mainApp.showView("view/AppointmentList.fxml", this);
	}
	
	private boolean isValid(){
		String text = "";
		boolean valid = true;
		Alert alert = new Alert(AlertType.WARNING);
		
		if(patientField.getText().equals("") || patientField == null){
			text += "Campo Paciente não pode ser vazio\n";
			valid = false;
		}
		if (doctorField.getText().equals("") || doctorField == null){
			text += "Campo Médico não pode ser vazio\n";
			valid = false;
		}
		if(dateField.getValue() == null){
			text += "Selecione uma data\n";
			valid = false;
		}
		if(!valid){
			alert.setTitle("Campos não preenchidos");
			alert.setHeaderText(text);
			alert.showAndWait();
		}
			
		return valid;
	}
}
