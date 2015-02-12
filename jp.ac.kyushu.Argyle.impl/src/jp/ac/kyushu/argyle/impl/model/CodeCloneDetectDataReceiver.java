package jp.ac.kyushu.argyle.impl.model;


import yoshikihigo.tinypdg.scorpio.Scorpio;

public class CodeCloneDetectDataReceiver extends DataReceiver{
	private static CodeCloneDetectDataReceiver CCDDReceiver = new CodeCloneDetectDataReceiver();

	public boolean canOutput = false;
	private CodeCloneDetectDataReceiver(){
		
	}
	
	public static CodeCloneDetectDataReceiver getInstance(){
		return CCDDReceiver;
	}

	public void output(String path){
		Scorpio.main(super.getParameterList().toArray(new String[0]));
	}
}
