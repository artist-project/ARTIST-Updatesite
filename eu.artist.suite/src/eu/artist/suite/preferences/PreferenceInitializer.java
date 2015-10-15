package eu.artist.suite.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import eu.artist.suite.ARTISTSuite;


public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public PreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = ARTISTSuite.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_BOOLEAN.getValue(), true);
		store.setDefault(PreferenceConstants.P_CHOICE.getValue(), "choice2");
		store.setDefault(PreferenceConstants.P_STRING.getValue(), "Default value");
		
		store.setDefault(PreferenceConstants.ARTIST_USE_INTERNAL_BROWSER.getValue(), false);
		
		store.setDefault(PreferenceConstants.TFT_REPORT_PATH.getValue(), "");
		
		store.setDefault(PreferenceConstants.MAT_URL.getValue(), "http://54.196.142.179:8080/ArtistEva/");
		store.setDefault(PreferenceConstants.MPT_URL.getValue(), "http://147.102.19.204:8082/eu.artist.methodology.mpt.webapp/");
		store.setDefault(PreferenceConstants.CT_URL.getValue(), "http://54.158.226.57:8080/ArtistCer/");
		
	    store.setDefault(PreferenceConstants.MPT_URL.getValue(), "http://147.102.19.204:8082/eu.artist.methodology.mpt.webapp/");
	    store.setDefault(PreferenceConstants.MPT_MAT_REPORT_PATH.getValue(), "");
	    store.setDefault(PreferenceConstants.MPT_ROLE.getValue(), PreferenceConstants.MPT_DEVELOPER_ROLE.getText());
	}

}
