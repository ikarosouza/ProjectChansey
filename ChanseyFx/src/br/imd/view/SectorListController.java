package br.imd.view;

import java.util.List;

import br.imd.MainApp;
import br.imd.dao.SectorDao;
import br.imd.model.Doctor;
import br.imd.model.Sector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SectorListController extends GenericController{
	@FXML
	private TableView<Sector> table;
	@FXML
	private TableColumn<Sector, String> nameColumn;
	@FXML
	private TableColumn<Sector, Number> numBedsColumn;
	@FXML
	private TextField nameSearchField;
	
	private SectorDao dao = new SectorDao();
	
	private MainApp mainApp;
	
	private SectorFormController formController;

	public SectorListController() {
		
	}
	
	@FXML
	private void initialize(){
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
		numBedsColumn.setCellValueFactory(cellData -> cellData.getValue().numBeds());
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		handleFillTable();
	}
	
	@FXML
	public void openSectorForm(){
		mainApp.showView("view/SectorForm.fxml", this);
	}
	
	@FXML
	public void handleEditSector(){
		Alert alert;

		Sector sector = table.getSelectionModel().getSelectedItem();
		if (sector != null) {
			mainApp.showView("view/SectorForm.fxml", this);
			formController.handleLoadSector(sector);
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum médico selecionado");
			alert.setHeaderText("Selecione um médico para editar");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleDeleteSector(){
		Alert alert;
		Sector sector = table.getSelectionModel().getSelectedItem();
		if(sector != null){
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Tem certeza que deseja deletar esse setor?");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK){
				table.getItems().remove(sector);
				dao.remove(sector);
			}
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nenhum médico selecionado");
			alert.setHeaderText("Selecione um setor para deletar");
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void handleSearch(){
		List<Sector> sectors = dao.search(nameSearchField.getText());
		ObservableList<Sector> sectorsResult = FXCollections.observableArrayList();
		for(Sector sector : sectors){
			sectorsResult.add(sector);
		}
		table.getItems().clear();
		table.setItems(sectorsResult);
	}
	
	@FXML 
	public void handleFillTable(){
		nameSearchField.setText("");
		ObservableList<Sector> sectors = FXCollections.observableArrayList();
		for (Sector sector : dao.list()) {
			sectors.add(sector);
		}
		table.getItems().clear();
		table.setItems(sectors);
	}
	
	@Override
	public void setOtherViewController(GenericController otherViewController) {
		if(otherViewController instanceof SectorFormController){
			this.formController = (SectorFormController) otherViewController;
		}		
	}
}
