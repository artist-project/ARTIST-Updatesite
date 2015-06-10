package eu.artist.suite.project;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2m.atl.adt.AtlNature;

import eu.artist.suite.utils.WorkspaceHelper;

public class ARTISTProjectSupport {
	
	
	/**
	 * For ARTIST project we need to: - retrieve the default created Eclipse Java project - add
	 * the ATL project nature - create the folder structure
	 * 
	 * @param projectName
	 *
	 * @return
	 */
	public static IProject createARTISTProject(String projectName) {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);
		IProject project = null;
		try {
			project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);
			if (project.exists()){
				addATLNature(project);
				String[] paths = { 
					"src/java", 
					"src/dotnet", 
					"src/test", 
					"src/webapp", 
					"feasibility_reports/maturity", 
					"feasibility_reports/technical", 
					"feasibility_reports/business", 
					"models/discovery", 
					"models/understanding", 
					"models/modernisation", 
					"models/goals", 
					"profiles"
				}; 
				WorkspaceHelper.addToProjectStructure(project, paths);
				WorkspaceHelper.refreshTargetProject(project);
			}
		} catch (CoreException e) {
			e.printStackTrace();
			project = null;
		}
		return project;
	}


	private static void addATLNature(IProject project) throws CoreException {
		WorkspaceHelper.addNature(AtlNature.ATL_NATURE_ID, project);
	}	
}
