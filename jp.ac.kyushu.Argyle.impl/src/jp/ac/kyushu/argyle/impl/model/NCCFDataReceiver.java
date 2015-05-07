package jp.ac.kyushu.argyle.impl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import journal.nccf.thread.exe.Execute;

@Data
@EqualsAndHashCode(callSuper=false)
public class NCCFDataReceiver extends DataReceiver{

	private static NCCFDataReceiver NCCFReceiver = new NCCFDataReceiver();

	private String importFile;
	private int threadNum;
	
	private NCCFDataReceiver(){
		
	}
	
	public static NCCFDataReceiver getInstance(){
		return NCCFReceiver;
	}

	@Override
	protected String[] setParameter(String path) {
		
		String[] para = {String.valueOf(threadNum), importFile, path};
		return para;
	}

	@Override
	protected void invoke(String[] parameters) {
		Execute.main(parameters);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		importFile="";
		threadNum = 0;
	}

	@Override
	public boolean canOutput() {
		if(!importFile.isEmpty() && threadNum>0){
			return true;
		}
		return false;
	}
}