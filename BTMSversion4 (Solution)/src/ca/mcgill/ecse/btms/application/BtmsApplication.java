package ca.mcgill.ecse.btms.application;

import ca.mcgill.ecse.btms.model.BTMS;
import ca.mcgill.ecse.btms.persistence.BtmsPersistence;
import ca.mcgill.ecse.btms.view.BtmsPage;

public class BtmsApplication {
	
	private static BTMS btms;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BtmsPage().setVisible(true);
            }
        });
	}

	public static BTMS getBtms() {
		if (btms == null) {
			// load model
			btms = BtmsPersistence.load();
		}
 		return btms;
	}
	
}
