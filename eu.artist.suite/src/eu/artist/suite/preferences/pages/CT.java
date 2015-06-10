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

public class CT extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private StringFieldEditor ctURL;

	public CT() {
		super(GRID);
		setPreferenceStore(ARTISTSuite.getDefault().getPreferenceStore());
		setDescription("CT runtime preferences");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		
		//URLs for web tools group
		Group urls = new Group(getFieldEditorParent(),SWT.NONE);
		urls.setText("URL for CT");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);
		gd.horizontalSpan = 3;
		urls.setLayoutData(gd);
		urls.setLayout(new GridLayout(1,false));
				
		ctURL = new StringFieldEditor(PreferenceConstants.CT_URL.getValue(), PreferenceConstants.CT_URL.getText(), urls); 
		addField(ctURL);
	}
	
	@Override
	protected void checkState() {
		super.checkState();

		if (ctURL.getStringValue()!= null && !ctURL.getStringValue().equals("")) {
			String[] schemes = {"http","https"};
			UrlValidator urlValidator = new UrlValidator(schemes);

			if (urlValidator.isValid(ctURL.getStringValue())) {
				setErrorMessage(null);
				setValid(true);
			} else {

				if (!urlValidator.isValid(ctURL.getStringValue())) {  		  
					setErrorMessage("CT URL is not a valid URL!");
				} 
				setValid(false);
			}

		} else {

			setErrorMessage("CT URL cannot be blank!");
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