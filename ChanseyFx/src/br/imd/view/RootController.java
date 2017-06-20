package br.imd.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RootController extends GenericController{
	private HBox hbox;
	private ImageView image;
	private VBox root;
	
	@FXML
    private void initialize() {
    }
	
	@FXML
	public void openHomeView(){
		HBox hboxNew = (HBox) root.getChildren().get(1);
        hboxNew.getChildren().remove(1);
        hboxNew.getChildren().add(1, image);
	}
	
	@FXML
	public void openPatientView(){
		mainApp.showView("view/PatientList.fxml", this);
	}
	
	@FXML
	public void openSectorView(){
		mainApp.showView("view/SectorList.fxml", this);
	}
	
	@FXML
	public void openSpecialtyView(){
		mainApp.showView("view/SpecialtyList.fxml", this);
	}
	
	@FXML
	public void openDoctorView(){
		mainApp.showView("view/DoctorList.fxml", this);
	}
	
	@FXML
	public void openAppointmentView(){
		mainApp.showView("view/AppointmentList.fxml", this);
	}
	
	@FXML
	public void openReportsView(){
		mainApp.showView("view/Reports.fxml", this);
	}

	public void setHbox(HBox hbox) {
		this.hbox = hbox;
		image = (ImageView) hbox.getChildren().get(1);
	}

	public void setRoot(VBox root) {
		this.root = root;
	}

}
