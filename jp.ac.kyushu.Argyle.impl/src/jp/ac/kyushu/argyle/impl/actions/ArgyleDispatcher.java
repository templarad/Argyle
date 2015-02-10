package jp.ac.kyushu.argyle.impl.actions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;

import jp.ac.kyushu.argyle.impl.Agl;
import jp.ac.kyushu.argyle.impl.ArgyleMethodList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		Method methodArray[] = ArgyleMethodList.class.getMethods();
		List<Method> methodList = new ArrayList<Method>();
		for (Method method: methodArray){
			if(method.getAnnotation(Agl.class) !=null ){
				methodList.add(method);
			}
		}
		methodList.forEach(e->log.debug(e.getName()+ e.getAnnotation(Agl.class)));
		Iterator<Method> methodIter = methodList.iterator();
		while(methodIter.hasNext()){
			Method method = methodIter.next();
			
			Class<?> paraTypes[] = method.getParameterTypes();
			for(Class<?> type : paraTypes){
				log.debug("{} para: {}", method.getName(), type.getName());
			}
			
		}
//		if(methodList[0].getAnnotation(jp.ac.kyushu.argyle.impl.Agl.class) != null){
//			Class<?> types[] = methodList[0].getParameterTypes();
//			
//		}
//		Annotation[] ans = ArgyleController.class.getAnnotationsByType(Agl.class);
		
	}
}
