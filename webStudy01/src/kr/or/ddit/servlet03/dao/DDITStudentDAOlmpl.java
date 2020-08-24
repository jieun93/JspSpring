package kr.or.ddit.servlet03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DDITStudentVO;
import sun.security.jca.GetInstance;

public class DDITStudentDAOlmpl implements IDDITStudentDAO {
	// 싱글톤 만들기 
	private static IDDITStudentDAO self;
	public static IDDITStudentDAO getInstance() {
		if(self == null) self = new DDITStudentDAOlmpl();
		return self;
	}
	
	// 코드값을 생성하기 위해서 만든것 
	private String generateCode(DDITStudentVO vo) {
		String genCode = null;
		StringBuffer sql = new StringBuffer();
		// 데이터 베이스 코드 부분 가져오는 거  출력형식 'S001'이렇게 만들어주는거 
        sql.append(" SELECT 'S' || LPAD(TO_NUMBER(SUBSTR(MAX(CODE), 2))+1, 3, '0') ");
        sql.append(" FROM DDITSTUDENT                                             ");
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
		    	ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				genCode = rs.getString(1);
			}
			vo.setCode(genCode);
			return genCode;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	private int insertStdLicense(DDITStudentVO vo) {
		// 자격증 받아오는거 
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO STD_LICENSE(STD_CODE, LIC_CODE) VALUES (?,?)");
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				int cnt = 0;
				for(String tmp : vo.getLic_codes()) {
					pstmt.setString(1, vo.getCode());
					pstmt.setString(2, tmp);
					cnt += pstmt.executeUpdate();
				}
				return cnt;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	
	
	// 학생 등록
	@Override
	public int insertStudent(DDITStudentVO vo) {
		// 학생을 등록하면 학생의 코드의 숫자가 1씩 올라가도록 셋팅
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT ALL");
		sql.append(" INTO DDITSTUDENT ( CODE, NAME, BIRTHDAY, AGE, GR_CODE, GEN, CAREER)");
		sql.append(" VALUES (? ,?, TO_DATE(?, 'YYYY-MM-DD'),?,?,?,?) ");
		if(vo.getLic_codes() != null) {
			for(String tmp :vo.getLic_codes()) {
				sql.append(" INTO STD_LICENSE(STD_CODE,LIC_CODE) VALUES (?,?)"); // 쿼리 파라미터 개수가 동적으로 바뀜 
				
			}
		}
		sql.append("select * from dual");
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			generateCode(vo);
			int i = 1;
			pstmt.setString(i++, vo.getCode());
			pstmt.setString(i++, vo.getName());
			pstmt.setString(i++, vo.getBirthday());
			pstmt.setInt(i++, vo.getAge());
			pstmt.setString(i++, vo.getGrade());
			pstmt.setString(i++, vo.getGen());
			pstmt.setString(i++, vo.getCareer());
			if(vo.getLic_codes()!= null) {

			 for(String lic_code : vo.getLic_codes()) {
								pstmt.setString(i++, vo.getCode());
								pstmt.setString(i++, lic_code);
							}
						}
						int cnt = pstmt.executeUpdate();
						return cnt;
					}
					catch(SQLException e) {
						throw new RuntimeException(e);
					}
				}
	
	// 전체 학생 조회 
	@Override
	public List<DDITStudentVO> selectStudentList() {
		
		 List<DDITStudentVO> stlist =  new ArrayList<>();
		 
		 StringBuffer sql = new StringBuffer();
		 sql.append("SELECT CODE, NAME,  AGE, DECODE(GEN, 'M','남', '여')GEN");
		 sql.append(" FROM DDITSTUDENT");
		 
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				
		){
			ResultSet rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				DDITStudentVO vo = new DDITStudentVO();
				stlist.add(vo);
				vo.setCode(rs.getString("CODE"));
				vo.setName(rs.getString("NAME"));
				vo.setAge(rs.getInt("AGE"));
				vo.setGen(rs.getString("GEN"));
			}
			return stlist;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	// 한명의 학생 조회
	@Override
	public DDITStudentVO selectStudent(String code) {
//		DDITStudentVO vo = null; 
		StringBuffer sql = new StringBuffer();
		  sql.append(" SELECT A.CODE, NAME, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') BIRTHDAY, AGE, ");
	      sql.append("         B.TEXT GRADE, DECODE(GEN, 'M', '남', '여') GEN,              ");
	      sql.append("         CAREER, D.TEXT LICENSE                                         ");
	      sql.append(" FROM DDITSTUDENT A INNER JOIN GRADE B ON (GR_CODE = B.CODE)         ");
	      sql.append("        LEFT OUTER JOIN STD_LICENSE C ON(A.CODE = C.STD_CODE)        ");
	      sql.append("        LEFT OUTER JOIN LICENSE D ON (C.LIC_CODE = D.CODE)           ");
	      sql.append(" WHERE A.CODE = ?                                               ");
		try(                                                                                  
		 Connection conn = ConnectionFactory.getConnection();
		 PreparedStatement stmt = conn.prepareStatement(sql.toString());
				
			){
			stmt.setString(1, code);
			
			ResultSet rs = stmt.executeQuery();
			
			Map<String, DDITStudentVO> resultMap = new HashMap<>();
			
			while(rs.next()) {
				String tmp = rs.getString(1);
				DDITStudentVO vo = resultMap.get(tmp);
				if(vo==null) {
					vo = new DDITStudentVO();
					resultMap.put(tmp, vo);
				}
				
				vo.setCode(tmp);
				vo.setName(rs.getString(2));
				vo.setBirthday(rs.getString(3));
				vo.setAge(rs.getInt(4));
				vo.setGrade(rs.getString(5));
				vo.setGen(rs.getString(6));
				vo.setCareer(rs.getString(7));
				List<String> license =  vo.getLicense();
				if(license == null) {
					license = new ArrayList<>();
					vo.setLicense(license);
				}
				
				license.add(rs.getString("license"));
				
			}
			if(resultMap.values().size()>0) // 사이즈가 0 이면 조회 안됨/ 0이 아니면 조회가 됨
			{
				return resultMap.get(code);
			}else {
				
				return null;
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 삭제하려면  조인되어 있는 자격증 먼저 삭제 후 회원을 삭제 해야 한다. 
	private int deleteLicenses(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from std_license where std_code = ? ");
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){		pstmt.setString(1, code);
				return pstmt.executeUpdate();
				
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 학생 수정
	/* (non-Javadoc)
	 * @see kr.or.ddit.servlet03.dao.IDDITStudentDAO#updateStudent(kr.or.ddit.vo.DDITStudentVO)
	 */
	@Override
	public int updateStudent(DDITStudentVO vo) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update dditstudent                                   ");
		sql.append(" set name=?, birthday= to_date(?, 'yyyy-mm-dd'), age=?, gr_code=?, gen=?, career=? ");
		sql.append(" where code=? ");
		
				try (
					
					Connection conn = ConnectionFactory.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					){
						int i =1;
					
					pstmt.setString(i++, vo.getName());
					pstmt.setString(i++, vo.getBirthday());
					pstmt.setInt(i++, vo.getAge());
					pstmt.setString(i++, vo.getGrade());
					pstmt.setString(i++, vo.getGen());
					pstmt.setString(i++, vo.getCareer());
					pstmt.setString(i++, vo.getCode());
					pstmt.executeQuery();
					int cnt = pstmt.executeUpdate();
					cnt += deleteLicenses(vo.getCode());
					if(vo.getLic_codes()!=null)
						cnt += insertStdLicense(vo);
					return cnt;
				
					// cnt에 어떻게 값을 넣어줘야 하나???
					
					}catch(SQLException e) {
					 throw new RuntimeException(e);
				}
	}
	
	
	
	// 학생 삭제
	@Override
	public int deletStudent(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from DDITSTUDENT where code=?");
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			int cnt =  deleteLicenses(code);
			cnt += pstmt.executeUpdate();
			return cnt;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
