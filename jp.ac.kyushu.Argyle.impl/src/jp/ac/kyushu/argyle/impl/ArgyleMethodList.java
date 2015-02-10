package jp.ac.kyushu.argyle.impl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ArgyleMethodList {

	@Agl
	public void ImportMbox (String path){
		log.debug("Imported path is {}", path);
	}
	
	@Agl
	public void ExtractThread(){
		log.debug("Extract thread");
	}
	
	@Agl
	public void SetDetectMinSize(int asdf, String ok){
		//scorpio.Main("cxf/", "output/", "cxf.xml", 6, 2);
	}
}
