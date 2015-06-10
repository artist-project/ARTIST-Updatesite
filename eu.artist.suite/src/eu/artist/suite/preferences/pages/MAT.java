package eu.artist.suite.preferences.pages;

import org.apache.commons.validator.routines.UrlValidator;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.artist.suite.ARTISTSuite;
import eu.artist.suite.preferences.PreferenceConstants;

public class MAT extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private StringFieldEditor matUsername;
	private StringFieldEditor matPassword;
	private StringFieldEditor matURL;

	public MAT() {
		super(GRID);
		setPreferenceStore(ARTISTSuite.getDefault().getPreferenceStore());
		setDescription("MAT runtime preferences");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		
		//URLs for web tools group
		Group urls = new Group(getFieldEditorParent(),SWT.NONE);
		urls.setText("URL for MAT");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);
		gd.horizontalSpan = 3;
		urls.setLayoutData(gd);
		urls.setLayout(new GridLayout(1,false));
				
		matURL = new StringFieldEditor(PreferenceConstants.MAT_URL.getValue(), PreferenceConstants.MAT_URL.getText(), urls); 
		addField(matURL);
		
		//MAT credentials group
		Group matCredentials = new Group(getFieldEditorParent(),SWT.NONE);
		matCredentials.setText("MAT credentials");
		GridData gd_ = new GridData(GridData.FILL,GridData.FILL,true,false);
		gd_.horizontalSpan = 3;
		matCredentials.setLayoutData(gd_);
		matCredentials.setLayout(new GridLayout(1,false));

		matUsername = new StringFieldEditor(PreferenceConstants.MAT_USERNAME.getValue(), PreferenceConstants.MAT_USERNAME.getText(), matCredentials);
		addField(matUsername);

		matPassword = new StringFieldEditor(PreferenceConstants.MAT_PASSWORD.getValue(), PreferenceConstants.MAT_PASSWORD.getText(), matCredentials);    
		addField(matPassword);
	}
	
	@Override
	protected void checkState() {
		super.checkState();

		if (matURL.getStringValue()!= null && !matURL.getStringValue().equals("")) {
			String[] schemes = {"http","https"};
			UrlValidator urlValidator = new UrlValidator(schemes);

			if (urlValidator.isValid(matURL.getStringValue())) {
				setErrorMessage(null);
				setValid(true);
			} else {

				if (!urlValidator.isValid(matURL.getStringValue())) {  		  
					setErrorMessage("MAT URL is not a valid URL!");
				} 
				setValid(false);
			}

		} else {

			setErrorMessage("MAT URL cannot be blank!");
			setValid(false);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		//Same as above
		if (event.getProperty().equals(FieldEditor.VALUE)) {
			checkState();
		}  
	}

}
