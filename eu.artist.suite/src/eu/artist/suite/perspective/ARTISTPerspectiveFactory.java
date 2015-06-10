package eu.artist.suite.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ARTISTPerspectiveFactory implements IPerspectiveFactory {
	
	private static final float LEFT_RATIO = 0.25F;
	private static final float BOTTOM_RATIO = 0.75F;

	@Override
	public void createInitialLayout(IPageLayout layout) {

		String editorArea = layout.getEditorArea();

		// Add View in left side area of the editor space
		IFolderLayout leftLayout = layout.createFolder("leftLayout", IPageLayout.LEFT, LEFT_RATIO, editorArea);
		leftLayout.addView(IPageLayout.ID_PROJECT_EXPLORER);
		leftLayout.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		
		// Add View in the bottom area in separate way
		IFolderLayout bottomLayout = layout.createFolder("bottomLayout", IPageLayout.BOTTOM, BOTTOM_RATIO, editorArea);
		bottomLayout.addView(IPageLayout.ID_PROBLEM_VIEW);
	}

}
