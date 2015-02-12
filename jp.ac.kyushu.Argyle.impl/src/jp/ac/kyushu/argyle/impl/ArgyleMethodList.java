package jp.ac.kyushu.argyle.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a singleton class that you can get instance by <em>getInstance()</em> method.<br>
 * <b>Methods defined here </b>will be auto-matched to the DSL.<br>
 * If you have new DSL, you just need to define a new method here as the same name as DSL using an @Agl tag.<br>
 * Parameter will be auto-dispatched.
 * 
 * @author Templar
 *
 */
@Slf4j
public class ArgyleMethodList {

	private static ArgyleMethodList methodList = new ArgyleMethodList();
	
	private ArgyleMethodList(){
		
	}
	
	public static ArgyleMethodList getInstance(){
		return methodList;
	}
	@Agl
	public void ImportStyle(String path){
		log.debug("Imported path is {}", path);
	}
	@Agl
	public void ImportMbox (String path){
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void ExtractThread(){
		log.debug("Extract thread");
	}
	
	@Agl
	public void SetDetectMinSize(int size){
		//scorpio.Main("cxf/", "output/", "cxf.xml", 6, 2);
	}
}
