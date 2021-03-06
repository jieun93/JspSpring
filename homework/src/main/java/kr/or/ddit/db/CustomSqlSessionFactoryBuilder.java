package kr.or.ddit.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try(
				Reader reader =	Resources.getResourceAsReader("kr/or/ddit/db/mybatis/Configuration.xml");
			){
									//mysql설정 파일의 내요을 토대로  sqlSessionFactory를 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
