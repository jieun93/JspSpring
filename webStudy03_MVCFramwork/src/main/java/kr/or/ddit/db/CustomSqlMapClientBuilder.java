package kr.or.ddit.db;

import java.io.IOException;
import java.io.Reader;

import javax.management.RuntimeErrorException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class CustomSqlMapClientBuilder {
	 private static SqlMapClient sqlMapClient;
	
	static {
		// xml 을 읽어야 한다.
		try (
			Reader reader = Resources.getResourceAsReader("kr/or/ddit/db/ibatis/SqlMapConfig.xml");
		) {
			sqlMapClient	= SqlMapClientBuilder.buildSqlMapClient(reader);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	
	
}
