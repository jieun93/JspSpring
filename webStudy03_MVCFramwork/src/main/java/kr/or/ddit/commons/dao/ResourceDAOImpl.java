package kr.or.ddit.commons.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.ResourceVO;

public class ResourceDAOImpl implements IResourceDAO {

	 private SqlMapClient sql = CustomSqlMapClientBuilder.getSqlMapClient();
	
	@Override
	public List<ResourceVO> selectResourceList() {
		try {
			return sql.queryForList("Resource.selectResourceList");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
