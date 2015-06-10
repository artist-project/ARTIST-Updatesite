package eu.artist.suite.preferences.pages;

import org.apache.commons.validator.routines.UrlValidator;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
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

public class MPT extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private StringFieldEditor mpt_url_field;
	private RadioGroupFieldEditor roles_field;
	private FileFieldEditor mat_report_path;

	public MPT() {
		super(GRID);
	}

	public void createFieldEditors() {

		roles_field = new RadioGroupFieldEditor(
				PreferenceConstants.MPT_ROLE.getValue(),		
				PreferenceConstants.MPT_ROLE.getText(),

				1,

				new String[][] {
					{
						PreferenceConstants.MPT_GENERIC_ROLE.getText(), 
						PreferenceConstants.MPT_GENERIC_ROLE.getValue()
					},
					{
						PreferenceConstants.MPT_DEVELOPER_ROLE.getText(), 
						PreferenceConstants.MPT_DEVELOPER_ROLE.getValue()
					},

					{ 
						PreferenceConstants.MPT_BUSINESS_ROLE.getText(), 
						PreferenceConstants.MPT_BUSINESS_ROLE.getValue()
					}
				},

				getFieldEditorParent(),

				true);

		addField(roles_field);

		//URLs for web tools group
		Group urls = new Group(getFieldEditorParent(),SWT.NONE);
		urls.setText("URLs for MPT");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);
		gd.horizontalSpan = 3;
		urls.setLayoutData(gd);
		urls.setLayout(new GridLayout(1,false));

		mpt_url_field = new StringFieldEditor(PreferenceConstants.MPT_URL.getValue(), PreferenceConstants.MPT_URL.getText(), urls);   
		addField(mpt_url_field);

		//Reports group
		Group reports = new Group(getFieldEditorParent(),SWT.NONE);
		reports.setText("Customization reports");
		GridData _gd_ = new GridData(GridData.FILL,GridData.FILL,true,false);
		_gd_.horizontalSpan = 3;
		reports.setLayoutData(_gd_);
		reports.setLayout(new GridLayout(1,false));

		mat_report_path = new FileFieldEditor(PreferenceConstants.MPT_MAT_REPORT_PATH.getValue(), PreferenceConstants.MPT_MAT_REPORT_PATH.getText(), reports);   
		addField(mat_report_path);

	}

	protected void checkState() {
		super.checkState();

		if (mpt_url_field.getStringValue()!= null && !mpt_url_field.getStringValue().equals("")) {
			String[] schemes = {"http","https"};
			UrlValidator urlValidator = new UrlValidator(schemes);

			if (urlValidator.isValid(mpt_url_field.getStringValue())) {
				setErrorMessage(null);
				setValid(true);
			} else {

				if (!urlValidator.isValid(mpt_url_field.getStringValue())) {  		  
					setErrorMessage("MPT WebAPP URL is not a valid URL!");
				}

				setValid(false);
			}

		} else {

			setErrorMessage("MPT WebAPP URL cannot be blank!");
			setValid(false);
		}
	}

	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (event.getProperty().equals(FieldEditor.VALUE)) {
			checkState();
		}        
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(ARTISTSuite.getDefault().getPreferenceStore());
		setDescription("Set MPT runtime preferences.");
	}
}
