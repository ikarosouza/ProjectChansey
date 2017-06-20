package br.imd.view;

import br.imd.MainApp;

public class GenericController {
	protected MainApp mainApp;
	
//	Um controller de uma outra view generica que permite chamar metodos de um controller em outro
	protected GenericController otherViewController;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setOtherViewController(GenericController otherViewController) {
		this.otherViewController = otherViewController;
	}
}
