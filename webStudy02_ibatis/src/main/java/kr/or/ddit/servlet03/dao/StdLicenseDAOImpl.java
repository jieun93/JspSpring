package kr.or.ddit.servlet03.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.DDITStudentVO;

public class StdLicenseDAOImpl implements IStdLicenseDAO{
//	싱글톤 만드는거 
	private StdLicenseDAOImpl() {}
	private static StdLicenseDAOImpl self;
	public static StdLicenseDAOImpl getInstance() {
		if(self== null) self = new StdLicenseDAOImpl();
		return self;
	}
	
	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	
	//stdLicense.xml하고 연결
	
	@Override
	public int insertLicenses(DDITStudentVO vo) {
		try{
			// ibatis를 이용한 insert쿼리 실행
			// 1) selectKey로 PK를 쿼리 전에 생성하는 경우, insert 메소드로 실행
			//  생성된 pk가 반환됨 
			//  2) selectkey가 없는 단순 쿼리, update/delete 메소드로 실행
			// 영향을 받은 레코드수가 반환됨.
			
			return sqlMapClient.update("StdLicense.insertLicenses",vo);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteLicenses(String code) {
		try{
			return sqlMapClient.delete("StdLicense.deleteLicenses",code);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
