package jp.ac.kyushu.argyle.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;

import jp.ac.kyushu.argyle.impl.model.CodeCloneDetectDataReceiver;
import jp.ac.kyushu.argyle.impl.model.DataReceiver;
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
//=====================================
//	NCCF and scorpio
//=====================================	
	@Agl
	public void ImportGitLog(String path){	
		NCCFDataReceiver.getInstance().setImportFile(path);
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void ImportCodeDir(String path){	
		CodeCloneDetectDataReceiver.getInstance().setImportDir(path);
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void UseThread(int thread){	
		NCCFDataReceiver.getInstance().setThreadNum(thread);
		log.debug("useThread number is {}", thread);
	}
	
	@Agl
	public void SetThread(int thread){	
		CodeCloneDetectDataReceiver.getInstance().setThreadNum(thread);
		log.debug("setThread number is {}", thread);
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
		log.debug("DetectMinSize is {}", size);

	}
	
	@Agl
	public void DetectCodeClone(){
		CodeCloneDetectDataReceiver ccddR = CodeCloneDetectDataReceiver.getInstance();
		if (ccddR.canOutput()) {
			ccddR.execute();
		}else {
			MessageDialog.openError(
			        null,
			        "Error",
			        "No data can be output!");
		}
	}
	
	@Agl
	public void DefectPredict(){
		NCCFDataReceiver nccf = NCCFDataReceiver.getInstance();
		if (nccf.canOutput()) {
			nccf.execute();
		}else {
			MessageDialog.openError(
			        null,
			        "Error",
			        "No data can be output!");
		}
	}
//=====================================
//		MLMining
//=====================================
	@Agl
	public void RemoveMailQuote(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setRemoveMailQuote(true);
		mlm.execute();

	}
	
	@Agl
	public void ResolveMailDeveloper(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setResolveMailDeveloper(true);
		mlm.execute();
	}
	
	@Agl
	public void Output(String path){
		List<DataReceiver> dataRecList = new ArrayList<DataReceiver>();
		dataRecList.add(MLMiningDataReceiver.getInstance());
		dataRecList.add(NCCFDataReceiver.getInstance());
		dataRecList.add(CodeCloneDetectDataReceiver.getInstance());
		
		Iterator<DataReceiver> dataRecIter = dataRecList.iterator();
		while(dataRecIter.hasNext()){
			DataReceiver dataRec = dataRecIter.next();
			dataRec.setPath(path);
			log.debug("{} path setting: {}", dataRec.getClass().getSimpleName(), path);
		}
		
	}
}
