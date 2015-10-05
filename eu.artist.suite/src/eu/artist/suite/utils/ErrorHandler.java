package eu.artist.suite.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import eu.artist.suite.ARTISTSuite;

public class ErrorHandler {
	
	public static void showAndLogError(final Exception e, final String errorMessage, final Shell shell, final String dialogTitle, final String pluginID){
		
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				e.printStackTrace();
				String eMsg = errorMessage + "\nCheck error log in Error Log View and send these error log to the owners of the ARTIST plugin: " + pluginID; 
				IStatus status = new Status(Status.ERROR, pluginID, eMsg , e);
				ARTISTSuite.getDefault().getLog().log(status);
				
				EclipseWorkbenchHelper.showErrorDialog(shell, dialogTitle, eMsg);
			}
		});
		
	}

}
