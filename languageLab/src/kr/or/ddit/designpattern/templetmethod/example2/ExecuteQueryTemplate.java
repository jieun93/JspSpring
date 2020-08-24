package kr.or.ddit.designpattern.templetmethod.example2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;

public abstract class ExecuteQueryTemplate<T> {
	public  List<T> queryForList(String query, Object[] parameters){
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			List<T>  list = executeQuery(pstmt, parameters);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
		}
		
	public T queryForObject(String query,Object[] patameters){
		List<T> list =  queryForList(query, patameters);
		if(list != null && list.size()>0) {
					return list.get(0);
		}else {
			return null;
		}
	}
	
	protected abstract List<T> executeQuery(PreparedStatement pstmt, Object[] parameters) throws SQLException;
}
