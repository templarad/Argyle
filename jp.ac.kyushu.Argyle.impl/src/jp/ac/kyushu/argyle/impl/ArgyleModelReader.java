package jp.ac.kyushu.argyle.impl;

import jp.ac.kyushu.argyle.Model;
import jp.ac.kyushu.ui.internal.ArgyleActivator;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;

import com.google.inject.Injector;

public class ArgyleModelReader {
	protected static Resource resource;
	final private static Injector injector = ArgyleActivator.getInstance()
			.getInjector("jp.ac.kyushu.Argyle");
	
	public ArgyleModelReader(IResource agl){
		readResoure(agl);
	}
	public Model getModel(){
		return (Model) resource.getContents().get(0);
	}
	private static void readResoure(IResource agl) {
		XtextResourceSet rs = (XtextResourceSet) injector.getInstance(
				XtextResourceSetProvider.class).get(agl.getProject());
		rs.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

		resource = rs.getResource(URI.createPlatformResourceURI(
				agl.getFullPath().toString(), true), true);
	}
}
