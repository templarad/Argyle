package jp.ac.kyushu.argyle.impl.model;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import yoshikihigo.tinypdg.scorpio.Scorpio;Å@  //import Anko.main method

@Data
@EqualsAndHashCode(callSuper=false)
public class AnkoDataReceiver extends DataReceiver{
	private static AnkoDataReceiver ANKOReceiver = new AnkoDataReceiver();

	private String changeTrainData;
	private String useJoinData;
	private boolean changeTrainModel;
	private boolean useSimpleMetrics;

	
	private AnkoDataReceiver(){
		
	}
	
	public static AnkoDataReceiver getInstance(){
		return ANKOReceiver;
	}
	


	@Override
	protected String[] setParameter(String path) {
		List<String> parameterList = new ArrayList<String>();
		if(!changeTrainData.isEmpty()){
			parameterList.add("-- dataset");
			parameterList.add(changeTrainData);
		}
		
		if(!useJoinData.isEmpty()){
			parameterList.add("-- join");
			parameterList.add(useJoinData);
		}
		
		if(!changeTrainModel){
			parameterList.add("--model glm");
		}
		
		if(!useSimpleMetrics){
			parameterList.add("-s");
		}
		
		return parameterList.toArray(new String[0]);
	}

	@Override
	public void initialize() {
		changeTrainData="";
		useJoinData="";
		changeTrainModel = false;
		useSimpleMetrics = false;
		
	}

//=====================================
//	Anko é¿çs
//=====================================	
	
//	@Override
//	protected void invoke(String[] parameters) {
//		Scorpio.main(parameters);
//	}

	@Override
	public boolean canOutput() {
		if(!changeTrainData.isEmpty()  && !useJoinData.isEmpty() && changeTrainModel){
			return true;
		}
		return false;
	}

@Override
protected void invoke(String[] parameters) {
	// TODO Auto-generated method stub
	
}
}
