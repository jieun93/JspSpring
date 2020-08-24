package servlet06.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements iDataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBaseProperties(Map<String, Object> modelMap) {
		try(
				Connection conn = ConnectionFactory.getConnection();
				
				Statement stmt =  conn.createStatement();
				){
						// 쿼리 작성 
					// 쿼리 실행
				ResultSet rs =  stmt.executeQuery(sql); // 결과집합 ResultSet
				ResultSetMetaData rsmd =  rs.getMetaData();
				int count =rsmd.getColumnCount();// 컬럼 조회
				String[] headers = new String[count]; // 모든 데이터를 가짐 
				modelMap.put("headers", headers);
				for(int i =1; i<=count; i++){
				headers[i-1] = rsmd.getColumnName(i);
				}
				
				List<DataBasePropertyVO> propList = new ArrayList<>();
				modelMap.put("propList", propList);
			
				// 데이터 꺼내기 
				while(rs.next()){
					DataBasePropertyVO vo = new DataBasePropertyVO();
					// vo에 넣어주기 
					propList.add(vo);
					vo.setProperty_name(rs.getString("PROPERTY_NAME"));
					vo.setProperty_value(rs.getString(2));
					vo.setDescription(rs.getString("DESCRIPTION"));
				}
				return propList;
				
				}catch(SQLException e) {
					// 예외 종류 바꿔치기 
					throw new RuntimeException(e);
				}
	}			String sql = "SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION" + " FROM DATABASE_PROPERTIES";


}
