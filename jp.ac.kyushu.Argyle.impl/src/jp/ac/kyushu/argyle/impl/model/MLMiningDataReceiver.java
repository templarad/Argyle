package jp.ac.kyushu.argyle.impl.model;

public class MLMiningDataReceiver extends DataReceiver{
	private static MLMiningDataReceiver mlmReceiver = new MLMiningDataReceiver();

	public boolean canOutput = false;
	private MLMiningDataReceiver(){
		
	}
	
	public static MLMiningDataReceiver getInstance(){
		return mlmReceiver;
	}
	
	public void output(String path){
		
	}
}
