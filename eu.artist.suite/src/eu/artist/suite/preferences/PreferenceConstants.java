package eu.artist.suite.preferences;


/**
 * Constant definitions for plug-in preferences
 */
public enum PreferenceConstants {

	//TEST
	P_PATH("pathPreference", "testText"),
	P_BOOLEAN("booleanPreference", "testText"),
	P_CHOICE("choicePreference", "testText"),
	P_STRING("stringPreference", "testText"),
	
	//ARTIST
	ARTIST_USE_INTERNAL_BROWSER("artistBrowserPref", "&Use Eclipse's internal web browser to launch external ARTIST web tools."),
	
	//TFT
	TFT_REPORT_PATH("tftReportPath", "&Default TFT Report generation path: "),
	
	//MAT
	MAT_URL("mat_url", "MAT URL"),
	MAT_USERNAME("mat_username", "Username: "),
	MAT_PASSWORD("mat_password", "Password: "),
	
	//MPT
	MPT_URL("mpt_url", "URL for MPT Web App:"),
	MPT_ROLE("role", "Role options"),
	MPT_DEVELOPER_ROLE("developer", "Application developer"),
	MPT_BUSINESS_ROLE("business", "Business user"),
	MPT_GENERIC_ROLE("Generic", "Generic user"),
	MPT_MAT_REPORT_PATH ("mat_report_path", "Path to MAT report:"),
	
	CT_URL("ct_url", "CT URL");

	private String value;
	private String text;

//	static final Map<String,PreferenceConstants> lookup =  new HashMap<String, PreferenceConstants>();
//	static {
//		for (PreferenceConstants pC : PreferenceConstants.values())
//			lookup.put(pC.getValue(), pC);
//	}

	private PreferenceConstants(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}
	
	public String getText(){
		return text;
	}
	
//	public PreferenceConstants getConsantByValue(String value){
//		return lookup.get(value);
//	}
}
