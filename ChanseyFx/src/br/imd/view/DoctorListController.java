package br.imd.view;

import java.util.List;

import br.imd.MainApp;
import br.imd.dao.DoctorDao;
import br.imd.dao.SectorDao;
import br.imd.dao.SpecialtyDao;
import br.imd.model.Appointment;
import br.imd.model.Doctor;
import br.imd.model.Sector;
import br.imd.model.Specialty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DoctorListController extends GenericController {
	@FXML
	private TableView<Doctor> table;
	@FXML
	private TableColumn<Doctor, String> nameColumn;
	@FXML
	private TableColumn<Doctor, String> phoneColumn;
	@FXML
	private TableColumn<Doctor, String> crmNumberColumn;
	@FXML
	private TextField nameSearchField;
	@FXML
	private TextField phoneSearchField;
	@FXML
	private TextField crmNumberSearchField;
	@FXML
	private ComboBox<Specialty> specialtySearchField;
	@FXML
	private ComboBox<Sector> sectorSearchField;

	private DoctorDao dao = new DoctorDao();
	private SpecialtyDao specialtyDao = new SpecialtyDao();
	private SectorDao sectorDao = new SectorDao();

	private MainApp mainApp;

	private DoctorFormController formController;
	private DoctorOverviewController detailsController;

	public DoctorListController() {

	}

	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
		phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phone());
		crmNumberColumn.setCellValueFactory(cellData -> cellData.getValue().crmNumber());

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

		sectorSearchField.setItems(sectorsValues);
		specialtySearchField.setItems(specialtiesValues);
	}

	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		handleFillTable();
	}

	@FXML
	public void openDoctorForm() {
		mainApp.showView("view/DoctorForm.fxml", this);
	}

	@FXML
	public void handleEditDoctor() {
		Alert alert;

		Doctor doctor = table.getSelectionModel().getSelectedItem();
		if (doctor != null) {
			mainApp.showView("view/DoctorForm.fxml", this);
			formController.handleLoadDoctor(doctor);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum médico selecionado");
			alert.setHeaderText("Selecione um médico para editar");
			alert.showAndWait();
		}
	}

	@FXML
	public void handleDeleteDoctor() {
		Alert alert;
		Doctor doctor = table.getSelectionModel().getSelectedItem();
		if(doctor != null){
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Tem certeza que deseja deletar esse médico?");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK){
				table.getItems().remove(doctor);
				dao.remove(doctor);
			}
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum médico selecionado");
			alert.setHeaderText("Selecione um médico para deletar");
			alert.showAndWait();
		}
	}

	@FXML
	public void handleDoctorDetails() {
		Alert alert;
		Doctor doctor = table.getSelectionModel().getSelectedItem();
		if (doctor != null) {
			mainApp.showView("view/DoctorOverview.fxml", this);
			detailsController.setDoctor(doctor);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum médico selecionado");
			alert.setHeaderText("Selecione um médico");
			alert.showAndWait();
		}
	}

	@FXML
	public void handleSearch() {
		List<Doctor> doctors = dao.search(nameSearchField.getText(), phoneSearchField.getText(),
				crmNumberSearchField.getText(), specialtySearchField.getValue(), sectorSearchField.getValue());
		ObservableList<Doctor> doctorsResult = FXCollections.observableArrayList();
		for (Doctor doctor : doctors) {
			doctorsResult.add(doctor);
		}
		table.getItems().clear();
		table.setItems(doctorsResult);
	}

	@FXML
	public void handleFillTable() {
		nameSearchField.setText("");
		phoneSearchField.setText("");
		crmNumberSearchField.setText("");
		specialtySearchField.setValue(null);
		sectorSearchField.setValue(null);
		ObservableList<Doctor> doctors = FXCollections.observableArrayList();
		for (Doctor doctor : dao.list()) {
			doctors.add(doctor);
		}
		table.getItems().clear();
		table.setItems(doctors);
	}

	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if (otherViewController instanceof DoctorFormController) {
			this.formController = (DoctorFormController) otherViewController;
		} else if (otherViewController instanceof DoctorOverviewController) {
			this.detailsController = (DoctorOverviewController) otherViewController;
		}
	}
}
