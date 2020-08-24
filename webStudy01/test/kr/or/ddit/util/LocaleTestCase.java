package kr.or.ddit.util;

import static org.junit.Assert.*;

import java.util.Locale;

import org.apache.tomcat.jni.Local;
import org.junit.Test;

public class LocaleTestCase {
	//pojo(Plain Old Java Object)
	// 상위에 대한 제약이 없는거  , jdk만 있으면 어디서든 할 수 있는것
	@Test
	public void test1() {
		Locale defaultLoc =  Locale.getDefault();
		String localCode = defaultLoc.toLanguageTag();
		System.out.println(localCode);
		// localCode 복원하는거 
		Locale locale =Locale.forLanguageTag(localCode);
		// 같은 상태인지 비교하는거  
		System.err.println(locale == defaultLoc);	// 주소 비교
		System.err.println(locale.equals(defaultLoc));  // 상태 비교
		 
		
	}
	
	@Test
	public void what() {
		System.out.println("test");
		Locale defaultLoc =  Locale.getDefault();
		String lang = defaultLoc.getLanguage();
		String contury = defaultLoc.getCountry();
		System.out.printf("language : %s, contry : %s localeCode : %s\n",lang,contury, defaultLoc.toString());
		System.out.printf("language :%s, contry: %s\n ", defaultLoc.getDisplayLanguage(), defaultLoc.getDisplayCountry());
		Locale[] locales =  Locale.getAvailableLocales();
		for(Locale tmp :locales) {
			System.out.printf("language :%s, contry: %s\n ", tmp.getDisplayLanguage(tmp), tmp.getDisplayCountry(tmp));
			
		}
	}

}
