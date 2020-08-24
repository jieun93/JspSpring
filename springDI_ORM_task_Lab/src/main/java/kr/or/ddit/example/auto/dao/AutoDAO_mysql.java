package kr.or.ddit.example.auto.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AutoDAO_mysql implements IAutoDAO {

	@Override
	public String getRawData() {
		return "mysql에서 조회된 데이터";
	}

}
