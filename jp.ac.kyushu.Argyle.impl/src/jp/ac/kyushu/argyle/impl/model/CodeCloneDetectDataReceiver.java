package jp.ac.kyushu.argyle.impl.model;


import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

import yoshikihigo.tinypdg.scorpio.Scorpio;

@Data
@EqualsAndHashCode(callSuper=false)
public class CodeCloneDetectDataReceiver extends DataReceiver{
	private static CodeCloneDetectDataReceiver CCDDReceiver = new CodeCloneDetectDataReceiver();

	private String importDir;
	private int detectMinSize;
	private int threadNum;
	private CodeCloneDetectDataReceiver(){
		
	}
	
	public static CodeCloneDetectDataReceiver getInstance(){
		return CCDDReceiver;
	}
	


	@Override
	protected String[] setParameter(String path) {
		List<String> parameterList = new ArrayList<String>();
		if(!importDir.isEmpty()){
			parameterList.add("-d");
			parameterList.add(importDir);
		}
		
		parameterList.add("-o");
		parameterList.add(path);
				
		if(detectMinSize > 0){
			parameterList.add("-s");
			parameterList.add(String.valueOf(detectMinSize));
		}
		if(threadNum > 0){
			parameterList.add("-t");
			parameterList.add(String.valueOf(threadNum));
		}
		
		return parameterList.toArray(new String[0]);
	}

	@Override
	public void initialize() {
		importDir="";
		detectMinSize = 0;
		threadNum = 0;
		
	}
	
	@Override
	protected void invoke(String[] parameters) {
//		for(int i=0;i<parameters.length;i++)
//			System.out.println(parameters[i]);
		parameters = new String[8];
		parameters[0] = "-d";
		parameters[1] = "C:\\Anko\\ArgyleMethodList.java";		
		parameters[2] = "-o";
		parameters[3] = "C:\\Anko\\scorpio_test.txt";
		parameters[4] = "-s";
		parameters[5] = "1";
		parameters[6] = "-t";
		parameters[7] = "2";
		Scorpio.main(parameters);
	}

	@Override
	public boolean canOutput() {
		if(!importDir.isEmpty() && detectMinSize>0 && threadNum>0){
			return true;
		}
		return false;
	}
}