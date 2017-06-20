package br.imd.view;

import br.imd.dao.SpecialtyDao;
import br.imd.model.Specialty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SpecialtyFormController extends GenericController {
	@FXML
	private TextField nameField;

	private Specialty specialty = new Specialty();

	private SpecialtyDao dao = new SpecialtyDao();

	@FXML
	private void initialize() {
	}

	@FXML
	public void handleSaveSpecialty() {
		if (isValid()) {
			specialty.setName(nameField.getText());
			dao.save(specialty);
			handleCancel();
		}
	}

	public void handleLoadSpecialty(Specialty specialty) {
		this.specialty = specialty;
		nameField.setText(this.specialty.getName());
	}

	@FXML
	public void handleCancel() {
		nameField.setText("");
		specialty = new Specialty();
	}

	@FXML
	public void handleBack() {
		mainApp.showView("view/SpecialtyList.fxml", this);
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
