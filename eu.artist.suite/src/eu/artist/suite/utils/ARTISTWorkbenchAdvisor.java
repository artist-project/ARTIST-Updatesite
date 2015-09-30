package eu.artist.suite.utils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.WorkbenchAdvisor;


public class ARTISTWorkbenchAdvisor extends WorkbenchAdvisor{
	
	private Shell shell;

	@Override
	public String getInitialWindowPerspectiveId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void eventLoopException(Throwable exception) {
		// TODO Auto-generated method stub
		//super.eventLoopException(exception);
		
		MessageDialog.openWarning(shell, "unexpected", "caughtunhandled");
	}
}
