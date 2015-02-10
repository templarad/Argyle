package jp.ac.kyushu.argyle.impl.actions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.inject.Singleton;

import jp.ac.kyushu.argyle.impl.Agl;

@Singleton
public class ArgyleDispatcher {

	HashMap<String, String> dslToolMap = new HashMap<String, String>();
	private static ArgyleDispatcher aglDispatcher = new ArgyleDispatcher();

	private ArgyleDispatcher(){
		
	}
	
	public static ArgyleDispatcher getInstance(){
		return aglDispatcher;
	}
	
	public void dispatch(){
		Method methodList[] = ArgyleController.class.getMethods();
		if(methodList[0].getAnnotation(jp.ac.kyushu.argyle.impl.Agl.class) != null){
			Class<?> types[] = methodList[0].getParameterTypes();
			
		}
		Annotation[] ans = ArgyleController.class.getAnnotationsByType(Agl.class);
		
	}
}
