package jp.ac.kyushu.argyle.impl.model;

import java.util.List;

import lombok.Data;

@Data
public class DataReceiver {

	String importPath;
	
	List<String> parameterList;
	
	public void initialize(){
		setImportPath(null);
		setParameterList(null);
	}
}
