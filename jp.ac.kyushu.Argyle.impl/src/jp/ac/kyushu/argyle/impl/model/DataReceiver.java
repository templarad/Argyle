package jp.ac.kyushu.argyle.impl.model;

import java.io.File;
import java.io.IOException;

import lombok.Data;

import org.eclipse.jface.dialogs.MessageDialog;
@Data
public abstract class DataReceiver {

	private String path;
	
	boolean bool = false;
	
	public void execute(){
		
		File file = new File(path);
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				MessageDialog.openInformation(
				        null,
				        "Error",
				        "Output file path is invalid.");
				
				e.printStackTrace();
				return;
			}
		}
		
		invoke(setParameter(path));
	}
	
	/**
	 * This is <b>validation</b> method called by {@link jp.ac.kyushu.argyle.impl.ArgyleMethodList#Output(String path) Output} method.
	 * You need to override this method to validate that if the required parameters have been set.
	 * @return true If output can be called.
	 */
	public abstract boolean canOutput();
	
	/**
	 * This method is to set parameters into a String[] for invoking mining tools.<br>
	 * You need to check each parameter if it is empty. Only the one which is not empty can you set it to the parameter array.
	 * @param path The output file path.
	 * @return String[] the parameters array to invoke mining tools.
	 */
	protected abstract String[] setParameter(String path);
	
	/**
	 * This method will tell which mining tool and which method should be invoked.
	 * <br>
	 * @param parameters The parameters for call main method of mining tool.
	 */
	protected abstract void invoke(String[] parameters);
	
	/**
	 * Initialize every parameter in the class.
	 */
	public abstract void initialize();
	

}
