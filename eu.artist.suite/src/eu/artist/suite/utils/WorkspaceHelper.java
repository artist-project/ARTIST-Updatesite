package eu.artist.suite.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

public class WorkspaceHelper {
	/**
     * Recursively folder creation
     * @param folder
     * @throws CoreException
     */
    public static void createFolder(IFolder folder) throws CoreException {
        IContainer parent = folder.getParent();
        if (parent instanceof IFolder) {
            createFolder((IFolder) parent);
        }
        if (!folder.exists()) {
            folder.create(false, true, null);
        }
    }
    
    public static IFile createConfigurationFile(IContainer folder,
			String filename, String templateFilePath) {
    	IFile file = null;
    	try{
    		file = folder.getFile(new Path(filename));
    		
    		InputStream source = null;
    		if (templateFilePath != null)
    			source = Activator.getDefault().getBundle("eu.fiware.ficode.fia").getEntry(templateFilePath).openStream();
    		else 
    			source = new ByteArrayInputStream("".getBytes());
    		if (!file.exists()){
    			file.create(source, false, null);
    		}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	return file;
	}
    
    public static void addNature(String natureId, IProject project) throws CoreException {
        if (!project.hasNature(natureId)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = natureId;
            description.setNatureIds(newNatures);
            IProgressMonitor monitor = null;
            project.setDescription(description, monitor);
        }
    }
    
    /**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 * 
	 * @param newProject
	 * @param paths
	 * @throws CoreException
	 */
	public static void addToProjectStructure(IProject newProject,
			String[] paths) throws CoreException {
		for (String path : paths) {
			IFolder etcFolders = newProject.getFolder(path);
			WorkspaceHelper.createFolder(etcFolders);
		}
	}
	
	/**
	 * Just do the basics: create a basic project.
	 * 
	 * @param location
	 * @param projectName
	 */
	public static IProject createBaseProject(String projectName,
			URI location) {
		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);
		if (!newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace()
					.newProjectDescription(newProject.getName());
			if (location != null
					&& ResourcesPlugin.getWorkspace().getRoot()
							.getLocationURI().equals(location)) {
				projectLocation = null;
			}
			desc.setLocationURI(projectLocation);
			try {
				newProject.create(desc, null);
				if (!newProject.isOpen()) {
					newProject.open(null);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return newProject;
	}
	
	public static void setClassPathToNewLibs(String projectName, String[] jarPathList){
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        try {
            IJavaProject javaProject = (IJavaProject)project.getNature(JavaCore.NATURE_ID);
            IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
            List list = new LinkedList(java.util.Arrays.asList(rawClasspath));
            for(String path:jarPathList){
                String jarPath = path.toString();
                boolean isAlreadyAdded=false;
                for(IClasspathEntry cpe:rawClasspath){
                    isAlreadyAdded=cpe.getPath().toOSString().equals(jarPath);
                    if (isAlreadyAdded) break;
                }
                if (!isAlreadyAdded){
                    IClasspathEntry jarEntry = JavaCore.newLibraryEntry(new Path(jarPath),null,null);
                    list.add(jarEntry);
                }
            }
            IClasspathEntry[] newClasspath = (IClasspathEntry[])list.toArray(new IClasspathEntry[0]);
            javaProject.setRawClasspath(newClasspath,null);
        } catch (CoreException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Helper method that request to refresh the project where generated models have been placed
	 * @param targetProject The project to refresh.
	 * @throws CoreException
	 */
	static public void refreshTargetProject(IProject targetProject)
			throws CoreException {
		targetProject.refreshLocal(IResource.DEPTH_INFINITE, null);
	}
}
