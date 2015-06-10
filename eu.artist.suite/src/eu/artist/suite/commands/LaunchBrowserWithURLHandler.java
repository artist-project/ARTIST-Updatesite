package eu.artist.suite.commands;

import java.net.URL;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

import eu.artist.suite.ARTISTSuite;
import eu.artist.suite.preferences.PreferenceConstants;

public class LaunchBrowserWithURLHandler implements IHandler {
	
	private static IWebBrowser internalBrowser;

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		String urlIdentifier = event.getParameter("eu.artist.suite.commands.parameter.URLIdentifier");
		
		try {
			
			IPreferenceStore store = ARTISTSuite.getDefault().getPreferenceStore();
	        String url = store.getString(urlIdentifier);
	        
	        if (store.getBoolean(PreferenceConstants.ARTIST_USE_INTERNAL_BROWSER.getValue())){
	        	if (internalBrowser == null) {
	        		internalBrowser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(null);
	        	}
	        	internalBrowser.openURL(new URL(url));
	        } 
	        else {
	        	PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(url));
	        }
	        
	   } catch(Exception ex)  {
		   ex.printStackTrace();
	   }
		
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

}
