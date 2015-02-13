package jp.ac.kyushu.argyle.impl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MLMiningDataReceiver extends DataReceiver{
	private static MLMiningDataReceiver mlmReceiver = new MLMiningDataReceiver();

	private String importFile;
	private boolean removeMailQuote;
	private boolean CalcSocialMetrics;
	private boolean ResolveMailAlias;
	private boolean ResolveMailDeveloper;
	private boolean RemoveUselessMail;
	private MLMiningDataReceiver(){
		
	}
	
	public static MLMiningDataReceiver getInstance(){
		return mlmReceiver;
	}
	
	@Override
	protected String[] setParameter(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void invoke(String[] parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		importFile= "";
		removeMailQuote = false;
	}

	@Override
	public boolean canOutput() {
		if(!importFile.isEmpty() &&
				(removeMailQuote||CalcSocialMetrics||ResolveMailAlias || ResolveMailDeveloper || RemoveUselessMail)){
			return true;
		}
		return false;
	}
	
}
