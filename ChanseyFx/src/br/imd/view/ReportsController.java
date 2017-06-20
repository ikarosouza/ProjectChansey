package br.imd.view;

import br.imd.dao.AppointmentDao;
import br.imd.dao.DoctorDao;
import br.imd.dao.PatientDao;
import br.imd.dao.SectorDao;
import br.imd.dao.SpecialtyDao;
import br.imd.model.Appointment;
import br.imd.model.Doctor;
import br.imd.model.Patient;
import br.imd.model.Sector;
import br.imd.model.Specialty;
import br.imd.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ReportsController extends GenericController{
	@FXML
	private TextArea textArea;	
	
	private PatientDao patientDao = new PatientDao();
	private DoctorDao doctorDao = new DoctorDao();
	private SpecialtyDao specialtyDao = new SpecialtyDao();
	private SectorDao sectorDao = new SectorDao();
	private AppointmentDao appointmentDao = new AppointmentDao();
	
	@FXML
    private void initialize() {		
		
    }
	
	@FXML
	public void handlePatientReport(){
		textArea.setText("");
		String text = "";
		for(Patient patient : patientDao.list()){
			text += "Nome: " + patient.getName() + "\n" +
					"Telefone: " + patient.getPhone() + "\n" +
					"Contato de Emergencia: " + patient.getEmergencyNumber() + "\n" +
					"CPF: " + patient.getCpf() + " RG: " + patient.getRg() + "\n" +
					"Sexo: " + patient.getSex() + " Tipo Sanguineo: " + patient.getBloodType() + "\n" +
					"Altura: " + patient.getHeight() + " Peso: " + patient.getWeight() + "\n" +
					"Endereço: \n" +
					"Rua: " + patient.getAddress().getStreet() + " Nº" + patient.getAddress().getHouseNumber() + "\n" +
					"Bairro: " + patient.getAddress().getNeighborhood() + " - " + patient.getAddress().getState() + "\n" +
					"CEP: " + patient.getAddress().getZipCode() + "\n\n";
		}
		textArea.setText(text);
	}
	
	@FXML
	public void handleDoctorReport(){
		textArea.setText("");
		String text = "";
		for(Doctor doctor : doctorDao.list()){
			text += "Nome: " + doctor.getName() + "\n" +
					"Telefone: " + doctor.getPhone() + "\n" +
					"CRM: " + doctor.getCrmNumber() + "\n";
			String specialties = "";
			String sectors = "";
			for(Specialty specialty : doctor.getSpecialties()){
				specialties += specialty.getName() + ", ";
			}
			for(Sector sector : doctor.getSectors()){
				sectors += sector.getName() + ", ";
			}
			specialties = specialties.trim();
			specialties = specialties.substring(0, specialties.length()-1);
			sectors = sectors.trim();
			sectors = sectors.substring(0, sectors.length()-1);
			text += "Especialidades: " + specialties + "\n" +
					"Setores: " + sectors + "\n\n";
					
		}
		textArea.setText(text);
	}
	
	@FXML
	public void handleSectorReport(){
		textArea.setText("");
		String text = "";
		for(Sector sector : sectorDao.list()){
			text += "Nome: " + sector.getName() + "\n" +
					"Numero de Leitos: " + sector.getNumBeds() + "\n\n";
		}
		textArea.setText(text);
	}
	
	@FXML
	public void handleSpecialtyReport(){
		textArea.setText("");
		String text = "";
		for(Specialty specialty : specialtyDao.list()){
			text += "Nome: " + specialty.getName() + "\n\n";
		}
		textArea.setText(text);
	}
	
	@FXML
	public void handleAppointmentReport(){
		textArea.setText("");
		String text = "";
		for(Appointment appointment : appointmentDao.list()){
			text += "Paciente: " + appointment.getPatient().getName() + "\n" +
					"Médico: " + appointment.getDoctor().getName() + "\n" +
					"Data: " + DateUtil.format(appointment.getDate()) + "\n\n";
		}
		textArea.setText(text);
	}
}
