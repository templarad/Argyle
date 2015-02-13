package jp.ac.kyushu.argyle.impl.model;

import journal.nccf.thread.exe.Execute;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
		
		String[] para = {importFile, String.valueOf(threadNum), path};
		return para;
	}

	@Override
	protected void invoke(String[] parameters) {
		Execute.main(parameters);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canOutput() {
		if(!importFile.isEmpty()){
			return true;
		}
		return false;
	}
}
