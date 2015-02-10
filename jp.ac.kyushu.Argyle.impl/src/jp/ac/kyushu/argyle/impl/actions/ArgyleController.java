package jp.ac.kyushu.argyle.impl.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;

import jp.ac.kyushu.argyle.impl.ArgyleModelReader;
import jp.ac.kyushu.argyle.impl.basefunction.ProjectReader;
import lombok.extern.slf4j.Slf4j;
import jp.ac.kyushu.argyle.Import;
import jp.ac.kyushu.argyle.Model;


@Slf4j
public class ArgyleController {
	static IResource aglFile;
	private ArgyleController(){
		
	}
	
	public static void run(){
		aglFile = null;
		log.debug("It runs!");
		ArgyleDispatcher dispatcher = ArgyleDispatcher.getInstance();
		IProject project = ProjectReader.getProject();
		if (project == null) {
			MessageDialog.openInformation(
			        null,
			        "Error",
			        "To run the Argyle, you need to put a \"*.agl\" file into a project.");
			return;
		}
		
		//Looking for .agl file to execute
		try {
			project.accept(new IResourceVisitor() {
				
				@Override
				public boolean visit(IResource resource) throws CoreException {
					String ext = resource.getFileExtension();
					if(ext == null) return true;
					switch(ext){
					case "agl":
						aglFile = resource;
						return true;
					default:
						break;
					}	
					return true;
				}
			});
		} catch (CoreException e) {
			MessageDialog.openInformation(
			        null,
			        "Error",
			        "No file exsits here!");
			e.printStackTrace();
			return;
		}
		if(aglFile == null){
			MessageDialog.openInformation(
			        null,
			        "Error",
			        "No .agl exsits here!");
			return;
		}
		ArgyleModelReader aglreader = new ArgyleModelReader(aglFile);
		Model model = aglreader.getModel();
		model.getImports().forEach(imports->log.debug(imports.toString()));
		model.getCalc().forEach(imports->log.debug(imports.toString()));
		dispatcher.dispatch();
	}
}
