package jp.ac.kyushu.argyle.impl;

import org.eclipse.jface.dialogs.MessageDialog;

import jp.ac.kyushu.argyle.impl.model.CodeCloneDetectDataReceiver;
import jp.ac.kyushu.argyle.impl.model.MLMiningDataReceiver;
import jp.ac.kyushu.argyle.impl.model.NCCFDataReceiver;
import lombok.extern.slf4j.Slf4j;

/**
 * This is a singleton class that you can get instance by <em>getInstance()</em> method.<br>
 * <b>Methods defined here </b>will be auto-matched to the DSL.<br>
 * If you have new DSL, you just need to define a new method here as the same name as DSL using an @Agl tag.<br>
 * Parameter will be auto-dispatched.
 * 
 * @author Templar
 *
 */
@Slf4j
public class ArgyleMethodList {

	private static ArgyleMethodList methodList = new ArgyleMethodList();
	
	private ArgyleMethodList(){
		
	}
	
	public static ArgyleMethodList getInstance(){
		return methodList;
	}
	@Agl
	public void ImportMbox (String path){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setImportFile(path);
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void ImportGitLog(String path){
		NCCFDataReceiver.getInstance().setImportFile(path);
		
	}
	@Agl
	public void ExtractThread(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setExtractThread(true);
	}
	
	@Agl
	public void SetDetectMinSize(int size){
		CodeCloneDetectDataReceiver ccddR = CodeCloneDetectDataReceiver.getInstance();
		ccddR.setDetectMinSize(size);

	}
	
	@Agl
	public void RemoveMailQuote(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setRemoveMailQuote(true);

	}
	
	@Agl
	public void Output(String path){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		CodeCloneDetectDataReceiver ccdd = CodeCloneDetectDataReceiver.getInstance();
		if(mlm.canOutput()){
			mlm.output(path);
		} else if(ccdd.canOutput()){
			ccdd.output(path);
		} else {
			MessageDialog.openError(
			        null,
			        "Error",
			        "No data can be output!");
		}
		
	}
}
