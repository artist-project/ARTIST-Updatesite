package eu.artist.suite.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;

import eu.artist.suite.ARTISTSuite;

public class ErrorHandler {
	
	public static void showAndLogError(Exception e, String errorMessage, final Shell shell, String dialogTitle, String pluginID){
		
		e.printStackTrace();
		errorMessage =  errorMessage + "\nCheck error log in Error Log View and send these error log to the owners of the ARTIST plugin: " + pluginID;
		IStatus status = new Status(Status.ERROR, pluginID, errorMessage , e);
		ARTISTSuite.getDefault().getLog().log(status);
		
		EclipseWorkbenchHelper.showErrorDialog(shell, dialogTitle, errorMessage);
	}

}
