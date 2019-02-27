package ca.mcgill.ecse.btms.persistence;

import ca.mcgill.ecse.btms.model.BTMS;

public class BtmsPersistence {
	
	private static String filename = "data.btms";
	
	public static void save(BTMS btms) {
		PersistenceObjectStream.serialize(btms);
	}
	
	public static BTMS load() {
		PersistenceObjectStream.setFilename(filename);
		BTMS btms = (BTMS) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (btms == null) {
			btms = new BTMS();
		}
		else {
			btms.reinitialize();
		}
		return btms;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
