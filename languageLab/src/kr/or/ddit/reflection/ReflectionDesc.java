package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import kr.or.ddit.reflect.ReflectionTest;
// reflection 작업 
public class ReflectionDesc {
	public static void main(String[] args) {
		
		Object obj =  ReflectionTest.getObject();

		Class<?> objType = obj.getClass();
		Field[] fields =  objType.getDeclaredFields();
		for( Field fid: fields) {
			String name = fid.getName();
			Class<?> fidType = fid.getType();
			Object value;
			try {
//				방법1
//				fid.setAccessible(true);
//				value = fid.get(obj);
				
//				방법2
//				mem_id, getMem_id
//				String getterName = "get"+name.toUpperCase().substring(0, 1)+name.substring(1);
//		        Method getter =  objType.getDeclaredMethod(getterName);
//		        value =  getter.invoke(obj);
				
				
//				방법 3  bean 자바빈에서 유틸리티를 제공하는거 
				PropertyDescriptor pd = new PropertyDescriptor(name, objType);
				Method getter =  pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				if(fidType.isAssignableFrom(String.class)){
					setter.invoke(obj, "abc");
				}else if(fidType.isAssignableFrom(Character())) {
					
				}else if() {
					
				}else if() {
					
				}
				setter.invoke(obj, args)
				value = getter.invoke(obj);
				System.out.printf("%s %s=%s;\n" ,fidType, name, value);
				try {
					value = getter.invoke(obj);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}catch(IntrospectionException e) { // 모든예외를 하나로 맵핑한다.
				
			}
		}
		
		Method[] methods = objType.getMethods(); // 다가져오기 위햐서 배열로 받는다
		for(Method mtd : methods) {
			String name = mtd.getName();
			Class<?>[] pTypes = mtd.getParameterTypes();
			Class<?> retType =  mtd.getReturnType();
			System.out.printf("%s %s(%s);\n", retType, name, Arrays.toString(pTypes));
	}
}
}