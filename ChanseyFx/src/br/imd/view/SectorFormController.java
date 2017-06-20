package br.imd.view;

import br.imd.dao.SectorDao;
import br.imd.model.Sector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SectorFormController extends GenericController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField numBedsField;

	private Sector sector = new Sector();

	private SectorDao dao = new SectorDao();

	@FXML
	private void initialize() {
		numBedsField.setText("0");
	}

	@FXML
	public void handleSaveSector() {
		if (isValid()) {
			sector.setName(nameField.getText());
			sector.setNumBeds(Integer.parseInt(numBedsField.getText()));
			dao.save(sector);
			handleCancel();
		}
	}

	public void handleLoadSector(Sector sector) {
		this.sector = sector;
		nameField.setText(this.sector.getName());
		numBedsField.setText(this.sector.getNumBeds() + "");
	}

	@FXML
	public void handleCancel() {
		sector = new Sector();
		nameField.setText("");
		numBedsField.setText("");
	}

	@FXML
	public void handleBack() {
		mainApp.showView("view/SectorList.fxml", this);
	}

	private boolean isValid() {
		String text = "";
		boolean valid = true;
		Alert alert = new Alert(AlertType.WARNING);

		if (nameField.getText().equals("") || nameField == null) {
			text += "Campo Nome não pode ser vazio\n";
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
