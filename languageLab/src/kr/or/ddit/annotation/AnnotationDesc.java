package kr.or.ddit.annotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import kr.or.ddit.annotation.sample.Sample1WithAnnotatoin;
import kr.or.ddit.annotation.stereotype.FirstMarkerAnnotation;
import kr.or.ddit.annotation.stereotype.SecondSigleValueAnnotation;
import kr.or.ddit.annotation.stereotype.URIMapping;
import kr.or.ddit.utils.ReflectionUtils;

/**
 *  comment : 주석의 대상이 사람 
 *  annotation : 주석의 대상  사람 + 컴파일러(system)
 *  	- 코드에 대한 메타 데이터를 대상에게 전달하기 위한 특수한 주석
 *  1. Marker annotation : 어노테이션이 부착된 타켓을 그룹핑 할때 사용
 *  2. SingleValue  annotation : value 라는 이름의 속성 하나를 가진 경우 
 *  3. MultiValue  annotation :  속성이 여러개를 갖고 있는경우 
 *  4.
 *  5.
 *  6.
 *  
 *  
 *  
 *  
 */
public class AnnotationDesc {
	public static void main(String[] args) throws ClassNotFoundException {
		String basePackage = "kr.or.ddit.annotation.sample";
		// 어노테이션의 하위만 받는거 ???
		Class<? extends Annotation> annotationType  =  FirstMarkerAnnotation.class;
		Map<Class<?>, Annotation> teatre =  ReflectionUtils.getClassesWithAnnotationAtBasePackages(annotationType, basePackage);
		
		System.out.println(targets);
		 ReflectionUtils.getMethodsWithAnnotationAtClass(annotationType, annotationType, returnType, parameterTypes);
		Class<?>[] targets = makerAnnotationTracing(basePackage, annotationType);
//		for(Class<?> target : targets) { // 어떤 클래스안에 몇개가들어가 있는지 확인하는거 
//			
//			Class<? extends Annotation> mtdType = ThirdMutiValurAnnotation.class;
//			Method[] methods  = annotationTracingForMethod(target,mtdType);
//			for(Method mtd : methods) {
//				ThirdMutiValurAnnotation mtdAnno  = (ThirdMutiValurAnnotation)mtd.getAnnotation(mtdType);
//				System.err.println(mtdAnno.value()+":"+mtdAnno.number());
//			}
//			
//		}
	}

	private static Method[] annotationTracingForMethod(Class<?> target, Class<? extends Annotation> mtdType) {
		Method[] methods = null;
		List<Method> mtdList = new ArrayList<Method>();
		for(Method tmp  :target.getDeclaredMethods()) {
			Annotation anno =  tmp.getAnnotation(mtdType);
			if(anno != null) {
				mtdList.add(tmp);
			}
		}
		methods = new Method[mtdList.size()];
		return mtdList.toArray(methods);
		
	}

	private static Class<?>[] makerAnnotationTracing(String basePackage, Class<? extends Annotation> annotationType) throws ClassNotFoundException {
		Class<?>[] targets = null;
		String classPath = '/'+basePackage.replace('.', '/');
		String systemPath =  AnnotationDesc.class.getResource(classPath).getFile();
		File baseFolder = new File(systemPath);
		String[] children = 	baseFolder.list((dir, name)->{
				return name.endsWith(".class");
			});
		List<Class<?>> targetList = new ArrayList<>();
		for(String name : children) {
			int lastIdx = name.lastIndexOf(".class"); // .class를 찾는거 
			String qualifiedName = basePackage +"."+name.substring(0, lastIdx);
			System.out.println(qualifiedName);
			Class<?> target =  Class.forName(qualifiedName);
			Annotation annotation = target.getAnnotation(annotationType);
			if(annotation != null) {
				targetList.add(targets);				
			}
		}
		targets = new Class<?>[targetList.size()];
		return targetList.toArray();
		
	}
	
	
	
}
