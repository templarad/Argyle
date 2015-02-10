package jp.ac.kyushu.argyle.impl.basefunction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
/**
 * A class for getting current project.
 * 
 * @author Templar
 *
 */
public class ProjectReader {
	
	private static ProjectReader pr = new ProjectReader();
	private ProjectReader(){
		
	}
	
	public static ProjectReader getInstance(){
		return pr;
	}
	public static IProject getProject(){
  
		IProject project = null;
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		Object object = activeEditor.getEditorInput().getAdapter(IFile.class);
		
        if(object != null){  
            project = ((IFile)object).getProject();  
        }
        return project; 
	}

}
