package jp.ac.kyushu.argyle.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;

import jp.ac.kyushu.argyle.impl.model.CodeCloneDetectDataReceiver;
import jp.ac.kyushu.argyle.impl.model.DataReceiver;
import jp.ac.kyushu.argyle.impl.model.MLMiningDataReceiver;
import jp.ac.kyushu.argyle.impl.model.NCCFDataReceiver;
import jp.ac.kyushu.argyle.impl.model.AnkoDataReceiver;
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

//=====================================
//	Coli (開発メーリングリストマイニングの前処理ツール)
//=====================================
	@Agl
	public void ImportMbox (String path){
		MLMiningDataReceiver.getInstance().setImportFile(path);
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void CalSocialMetrics(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setCalcSocialMetrics(true);
		mlm.execute();
		log.debug("CalSocialMetrics is ture");
	}
	
	@Agl
	public void DrawGraph(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setDrawGraph(true);
		mlm.execute();
		log.debug("DrawGraph is ture");
	}
	
	@Agl
	public void ResolveMailAlias(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setResolveMailAlias(true);
		mlm.execute();
		log.debug("ResolveMailAlias is ture");
	}
	
	@Agl
	public void ResolveMailDeveloper(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setResolveMailDeveloper(true);
		mlm.execute();
		log.debug("ResolveMailDeveloper is ture");
	}
	
	@Agl
	public void RemoveMailQuote(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setRemoveMailQuote(true);
		mlm.execute();
		log.debug("RemoveMailQuote is ture");
	}
	
	@Agl
	public void ExtractThread(){
		MLMiningDataReceiver mlm = MLMiningDataReceiver.getInstance();
		mlm.setExtractThread(true);
		log.debug("ExtractThread is ture");
	}	
	
//=====================================
//	scorpio
//=====================================	
		
	@Agl
	public void ImportCodeDir(String path){	
		CodeCloneDetectDataReceiver.getInstance().setImportDir(path);
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void SetThread(int thread){	
		CodeCloneDetectDataReceiver.getInstance().setThreadNum(thread);
		log.debug("setThread number is {}", thread);
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
	
//=====================================
//	NCCF 
//=====================================	
	
	@Agl
	public void ImportGitLog(String path){	
		NCCFDataReceiver.getInstance().setImportFile(path);
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void UseThread(int thread){	
		NCCFDataReceiver.getInstance().setThreadNum(thread);
		log.debug("useThread number is {}", thread);
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
//	anko
//=====================================	
	
	@Agl
	public void ChangeTrainData(String path){	
		AnkoDataReceiver anko = AnkoDataReceiver.getInstance();
		anko.setChangeTrainData(path);
		log.debug("ChangeTrainModel is {}", path);
	}
	
	@Agl
	public void UseJoinData(String path){	
		AnkoDataReceiver anko = AnkoDataReceiver.getInstance();
		anko.setUseJoinData(path);
		log.debug("UseJoinData is {}", path);
	}
	
	@Agl
	public void ChangeTrainModel(){	
		AnkoDataReceiver anko = AnkoDataReceiver.getInstance();
		anko.setChangeTrainModel(true);
		log.debug("ChangeTrainModel is ture");
	}

	@Agl
	public void UseSimpleMetrics(){	
		AnkoDataReceiver anko = AnkoDataReceiver.getInstance();
		anko.setUseSimpleMetrics(true);
		log.debug("UseSimpleMetrics is ture");
	}
	
//=====================================
//	public output
//=====================================	
	
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
		
	