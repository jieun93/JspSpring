package kr.or.ddit.servlet03.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;

public class GradeLicenseDAOImpl implements IGradeLicenseDAO {
	// 생성자 만들기 
	private GradeLicenseDAOImpl() {}
	private static GradeLicenseDAOImpl dao;
	public static GradeLicenseDAOImpl getInstance() {
		if(dao == null) dao =new GradeLicenseDAOImpl();
		return dao;
	}
	// SqlMapClient 메소드를 이용해서 dp의 작업을 수행 할 수 잇다. 
	private SqlMapClient sql = CustomSqlMapClientBuilder.getSqlMapClient();
	
	// 학번 리스트 조회?
	@Override
	public List<Map<String, Object>> selectGradeList() {
		try {
			// gradeAndLicense.xml 의 namespace를 연결하는거 
			return  sql.queryForList("GradeLicense.selectGradeList");
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	// 자격증을 조회 하는거
	@Override
	public List<Map<String, Object>> selectLicenseList() {
		try {
			 return sql.queryForList("GradeLicense.selectLicenseList");
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
	}

}
