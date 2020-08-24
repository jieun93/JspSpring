package kr.or.ddit.designpattern.templetmethod.example2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.property.adapter.PropertyDescriptor;

import kr.or.ddit.vo.MemberVO;

public class GeneralDAOExecuteQuery extends ExecuteQueryTemplate<MemberVO> {

	@Override
	protected List<MemberVO> executeQuery(PreparedStatement pstmt, Object[] parameters) throws SQLException {
		for(int i = 0; i <parameters.length; i++) {
			pstmt.setObject(i+1, parameters[i]);
		}
		
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmd =  rs.getMetaData();
		String[] columNames = new String[rsmd.getColumnCount()];
		for(int i = 0; i<columNames.length; i++) {
			columNames[i] = rsmd.getColumnName(i+1).toLowerCase();
		}
		
		List<MemberVO> list = new ArrayList<>();
		
		while(rs.next()) {
			MemberVO vo = new MemberVO();
			list.add(vo);
//			rs.getString(rs.getString("mem_id"));
			setProperties(vo, columNames, rs);
		}
		return list;
	}

	private void setProperties(MemberVO vo, String[] columNames) {
			for(String colName : columNames) {
				try {
					PropertyDescriptor pd = new PropertyDescriptor(colName, MemberVO.class);
					pd.getWriterMethod().invoke(vo, rs.getObject(colName));
				} catch (Exception e) {
					System.err.println(e.getMessage());
					continue;
				}
			}
	}

	
	
}
