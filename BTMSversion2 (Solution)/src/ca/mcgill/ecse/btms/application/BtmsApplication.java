package ca.mcgill.ecse.btms.application;

import ca.mcgill.ecse.btms.model.BTMS;
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
			// for now, we are just creating an empty BTMS
			btms = new BTMS();
		}
 		return btms;
	}
	
}
