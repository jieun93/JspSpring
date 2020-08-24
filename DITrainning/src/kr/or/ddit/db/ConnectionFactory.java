package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionFactory {
		private static String url;
		private static String user;
		private static String password;
		private static DataSource ds;

		static {
			// properties 파일 읽어오는거 
			ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbinfo");
			
			
			// 예외 코드를 바꿔치기 하는거 / 반드시 원본 예외를 전달해야 한다. 
//			try {
//				Class.forName(bundle.getString("driverClassName"));// 메모리상에 적재하기 위한 코드
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException(e);
//			}
			// 전혀 수정할 필요가 없는 코드와 수정 발생 가능 코드가 섞여있다면, 분리해야 함.
			 url = bundle.getString("url").trim();
			 user = bundle.getString("user").trim();
			 password = bundle.getString("password").trim();
			 
//				OracleDataSource ods =  new OracleDataSource(); // 직접적으로 사용하면 종속성이 발생
				//pooling을하려면 커넥션을 몇개를 만들건지를 알아얗낟. 
				BasicDataSource ods = new BasicDataSource();
				ods.setDriverClassName(bundle.getString("driverClassName"));
				ods.setUrl(url);
				ods.setUsername(user);
				ods.setPassword(password);
				ods.setInitialSize(5); // 처음 셋팅한 커넥션 수 
				ods.setMaxActive(10);// 최대 10개까지 커넥션 개수
				ods.setMaxWait(2000); // 2초동안 반납되는 커낵션의 갸수
				ds = ods;
				
			
		}
	
	public static Connection getConnection() throws SQLException {
		// 계정정보가 바뀌면 이부분을 고치면 된다.
		// 설정을 외부화 시키는 작업을 해야한다.
		
//		Connection conn = DriverManager.getConnection(url,user,password);
		return ds.getConnection();
	}

}
