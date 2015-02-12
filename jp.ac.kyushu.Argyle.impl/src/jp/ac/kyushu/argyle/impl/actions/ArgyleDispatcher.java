package jp.ac.kyushu.argyle.impl.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import jp.ac.kyushu.argyle.impl.Agl;
import jp.ac.kyushu.argyle.impl.ArgyleMethodList;
import jp.ac.kyushu.argyle.impl.exception.NoMatchMethodException;
import jp.ac.kyushu.argyle.impl.exception.ParameterUnmatchException;
import lombok.extern.slf4j.Slf4j;
/**
 * Matching DSL with method defined in <b>ArgyleMethodList</b>,<br>
 * and dispatching parameter from DSL to method.<br>
 * You can simply use it like:
 * <pre>
 * ArgyleDispatcher.dispatch("DSL", parameterList).
 * </pre>
 * 
 * <br>
 * @author Templar
 *
 */
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
	/**
	 * Method invocation using DSL name and parameters list.
	 * @param DSL
	 * @param paraList
	 * @throws ParameterUnmatchException caused by that if the parameters defined in <b>DSL</b> do not match those are define with @Alg.
	 * @throws IllegalAccessException Caused by method.invoke()
	 * @throws IllegalArgumentException Caused by method.invoke()
	 * @throws InvocationTargetException Caused by method.invoke()
	 * @throws NoMatchMethodException 
	 */
	public static void dispatch(String DSL, List<Object> paraList) 
			throws ParameterUnmatchException, 
			IllegalAccessException, 
			IllegalArgumentException, 
			InvocationTargetException, 
			NoMatchMethodException {
		
		Method[] methods = ArgyleMethodList.class.getMethods();
		for (Method method : methods) {
			if (!method.getName().equals(DSL) || method.getAnnotation(Agl.class) == null){
				continue;
			}
			Class<?>[] parameters = method.getParameterTypes();
			List<Object> parasForCall = new ArrayList<Object>();
			for (Class<?> parameter : parameters) {
				if (parameter.equals(int.class)) {
					List<Object> stringPara = paraList.stream()
							.filter((Object para) -> (para instanceof Integer))
							.filter((Object para) -> ((int)para > 0))
							.collect(Collectors.toList());
					if(stringPara.size() == 1){
						parasForCall.add(stringPara.get(0));
					} else {
						throw new ParameterUnmatchException(method.getName());
					}
					continue;
				}
				if (parameter.equals(String.class)) {
					List<Object> stringPara = paraList.stream()
							.filter((Object para) -> (para instanceof String))
							.collect(Collectors.toList());
					if(stringPara.size() == 1){
						parasForCall.add(stringPara.get(0));
					} else {
						throw new ParameterUnmatchException(method.getName());
					}
					continue;
				}
			}
			method.invoke(ArgyleMethodList.getInstance(), parasForCall.toArray());
			return;
		}
		throw new NoMatchMethodException(DSL);
	}

}
