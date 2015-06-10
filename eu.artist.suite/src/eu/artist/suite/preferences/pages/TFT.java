package eu.artist.suite.preferences.pages;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.artist.suite.ARTISTSuite;
import eu.artist.suite.preferences.PreferenceConstants;

public class TFT extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private DirectoryFieldEditor tftReportGenPath;

	public TFT() {
		super(GRID);
		setPreferenceStore(ARTISTSuite.getDefault().getPreferenceStore());
		setDescription("TFT runtime preferences");
	}


	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {

		tftReportGenPath = new DirectoryFieldEditor(PreferenceConstants.TFT_REPORT_PATH.getValue(), 
				PreferenceConstants.TFT_REPORT_PATH.getText(), getFieldEditorParent());

		addField(tftReportGenPath);
	}

	@Override
	protected void checkState() {
		super.checkState();

		//This check might not be necessary due to the usage of DirectoryFieldEditor 
		//		if(tftReportGenPath.getStringValue()!= null && !tftReportGenPath.getStringValue().equals("")){
		//			setErrorMessage(null);
		//			setValid(true);
		//		}else{
		//			setErrorMessage("Folder name cannot be blank!");
		//			setValid(false);
		//		}
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
