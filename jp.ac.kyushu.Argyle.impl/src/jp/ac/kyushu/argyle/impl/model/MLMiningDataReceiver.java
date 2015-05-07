package jp.ac.kyushu.argyle.impl.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import controller.Coli;
import org.eclipse.jdt.internal.jarinjarloader.JarRsrcLoader;

@Data
@EqualsAndHashCode(callSuper=false)
public class MLMiningDataReceiver extends DataReceiver{
	private static MLMiningDataReceiver mlmReceiver = new MLMiningDataReceiver();

	private String importFile;
	private boolean removeMailQuote;
	private boolean calcSocialMetrics;
	private boolean drawGraph;
	private boolean resolveMailAlias;
	private boolean resolveMailDeveloper;
	private boolean removeUselessMail;
	private boolean extractThread;
	private String repoDir;
	private MLMiningDataReceiver(){
		
	}
	
	public static MLMiningDataReceiver getInstance(){
		return mlmReceiver;
	}
	
	@Override
	protected String[] setParameter(String path) {
		List<String> parameterList = new ArrayList<String>();
		if(!importFile.isEmpty()){
			parameterList.add("-f");
			parameterList.add(importFile);
		}
		
		parameterList.add("-o");
		parameterList.add(path);
				
		if(!removeMailQuote){
			parameterList.add("-s");
		}
		
		return parameterList.toArray(new String[0]);
	}
	
	@Override
	protected void invoke(String[] parameters) {
		try {
			JarRsrcLoader.main(parameters);
		} catch (ClassNotFoundException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException
				| SecurityException | NoSuchMethodException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize() {
		importFile= "";
		removeMailQuote = false;
	}

	@Override
	public boolean canOutput() {
		if(importFile == null){
			return false;
		}
		if(!importFile.isEmpty() &&
				(removeMailQuote||calcSocialMetrics||resolveMailAlias || resolveMailDeveloper || removeUselessMail)){
			return true;
		}
		return false;
	}
	
}