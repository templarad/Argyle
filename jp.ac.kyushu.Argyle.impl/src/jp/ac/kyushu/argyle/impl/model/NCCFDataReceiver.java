package jp.ac.kyushu.argyle.impl.model;

import journal.nccf.thread.exe.Execute;
import jp.ac.kyushu.argyle.impl.ArgyleMethodList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class NCCFDataReceiver extends DataReceiver{

	private static NCCFDataReceiver NCCFReceiver = new NCCFDataReceiver();

	private String importFile;
	private int threadNum;
//	private String outputFile;
	private boolean DefectPredict;
	
	private NCCFDataReceiver(){
		
	}
	
	public static NCCFDataReceiver getInstance(){
		return NCCFReceiver;
	}

	@Override
	protected String[] setParameter(String path) {  //根据他们的来
		
		String[] para = {String.valueOf(threadNum), importFile, path};
//		String[] para = {String.valueOf(threadNum), importFile, outputFile, DefectPredict, path};
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
		//if(!importFile.isEmpty() && threadNum>0 && !outputFile.isEmpty() && DefectPredict == "DefectPredict;"){
		if(!importFile.isEmpty() && threadNum>0 && DefectPredict){
			log.debug("DefectPredict is {}",DefectPredict);
			return true;
		}
		return false;
	}

	public void output(String path) {
		// TODO Auto-generated method stub
		
	}
}
