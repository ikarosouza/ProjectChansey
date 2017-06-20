package br.imd.view;

import java.util.List;

import br.imd.MainApp;
import br.imd.dao.SpecialtyDao;
import br.imd.model.Doctor;
import br.imd.model.Specialty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SpecialtyListController extends GenericController{
	@FXML
	private TableView<Specialty> table;
	@FXML
	private TableColumn<Specialty, String> nameColumn;
	@FXML
	private TextField nameSearchField;
	
	private SpecialtyDao dao = new SpecialtyDao();
	
	private MainApp mainApp;
	
	private SpecialtyFormController formController;

	public SpecialtyListController() {
		
	}
	
	@FXML
	private void initialize(){
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		handleFillTable();
	}
	
	@FXML
	public void openSpecialtyForm(){
		mainApp.showView("view/SpecialtyForm.fxml", this);
	}
	
	@FXML
	public void handleEditSpecialty(){
		Alert alert;

		Specialty specialty = table.getSelectionModel().getSelectedItem();
		if (specialty != null) {
			mainApp.showView("view/SpecialtyForm.fxml", this);
			formController.handleLoadSpecialty(specialty);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhuma especialidade selecionado");
			alert.setHeaderText("Selecione uma especialidade para editar");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleDeleteSpecialty(){
		Alert alert;
		Specialty specialty = table.getSelectionModel().getSelectedItem();
		if(specialty != null){
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Tem certeza que deseja deletar essa especialidade?");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK){
				table.getItems().remove(specialty);
				dao.remove(specialty);
			}
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhuma especialidade selecionada");
			alert.setHeaderText("Selecione uma especialidade para deletar");
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void handleSearch(){
		List<Specialty> specialties = dao.search(nameSearchField.getText());
		ObservableList<Specialty> specialtiesResult = FXCollections.observableArrayList();
		for(Specialty specialty : specialties){
			specialtiesResult.add(specialty);
		}
		table.getItems().clear();
		table.setItems(specialtiesResult);
	}
	
	@FXML 
	public void handleFillTable(){
		nameSearchField.setText("");
		ObservableList<Specialty> specialties = FXCollections.observableArrayList();
		for (Specialty specialty : dao.list()) {
			specialties.add(specialty);
		}
		table.getItems().clear();
		table.setItems(specialties);
	}
	
	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if(otherViewController instanceof SpecialtyFormController){
			this.formController = (SpecialtyFormController) otherViewController;
		}		
	}
}
