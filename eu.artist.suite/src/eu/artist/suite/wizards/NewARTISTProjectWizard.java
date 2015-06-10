package eu.artist.suite.wizards;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageTwo;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import eu.artist.suite.project.ARTISTProjectSupport;


public class NewARTISTProjectWizard extends Wizard implements INewWizard {
	private IWorkbench workbench;
	private IStructuredSelection selection;
	private IWizardContainer container;
	private NewJavaProjectWizardPageOne firstPage;
	private NewJavaProjectWizardPageTwo secondPage;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	
	@Override
	public void addPages() {
		firstPage = new NewJavaProjectWizardPageOne();
		addPage (firstPage);
		secondPage = new NewJavaProjectWizardPageTwo(firstPage);
		addPage (secondPage);
		
		firstPage.init(getSelection(), getActivePart());
	}
	
	private IWorkbenchPart getActivePart() {
		IWorkbenchWindow activeWindow= getWorkbench().getActiveWorkbenchWindow();
		if (activeWindow != null) {
			IWorkbenchPage activePage= activeWindow.getActivePage();
			if (activePage != null) {
				return activePage.getActivePart();
			}
		}
		return null;
	}


	@Override
	public IWizardContainer getContainer() {
		return this.container;
	}

	@Override
	public String getWindowTitle() {
		return "New FIA Project creation";
	}

	@Override
	public void setContainer(IWizardContainer wizardContainer) {
		this.container = wizardContainer;
	}
	
	public IStructuredSelection getSelection() {
		return selection;
	}

	public IWorkbench getWorkbench() {
		return workbench;
	}
	
	@Override
	public IWizardPage getStartingPage() {
		return firstPage;
	}

	@Override
	public boolean performFinish() {
		try {
			secondPage.performFinish(new NullProgressMonitor());
			performFIAProjectFinish(firstPage.getProjectName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return true;
	}
	
	@Override
	public boolean performCancel() {
		try {
			secondPage.performCancel();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return true;
	}

	private void performFIAProjectFinish(String projectName) throws Exception {
		
		IProject project = ARTISTProjectSupport.createARTISTProject(projectName);

	}
	
	
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page.equals(firstPage)) {
			return secondPage;
		} else {
			return null;
		} 
	}

}
