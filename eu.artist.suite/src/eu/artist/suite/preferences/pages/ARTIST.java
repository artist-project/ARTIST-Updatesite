package eu.artist.suite.preferences.pages;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.artist.suite.ARTISTSuite;
import eu.artist.suite.preferences.PreferenceConstants;

public class ARTIST extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ARTIST() {
		super(GRID);
		setPreferenceStore(ARTISTSuite.getDefault().getPreferenceStore());
		setDescription("See sub-pages for settings.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {

		 addField(new BooleanFieldEditor(PreferenceConstants.ARTIST_USE_INTERNAL_BROWSER.getValue(),
				 PreferenceConstants.ARTIST_USE_INTERNAL_BROWSER.getText(), getFieldEditorParent()));
	}
}
