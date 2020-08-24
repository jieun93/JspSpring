package kr.or.ddit.servlet03.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import kr.or.ddit.vo.DDITStudentVO;
import sun.security.jca.GetInstance;

public class DDITStudentDAO implements IDDITStudentDAO {
//DB로 넘겨줘야 하지만 
// MAP으로 처리한 방법
	private static Map<String, DDITStudentVO> tableMap; // dao만 사용가능하다.
	static {
		tableMap = new LinkedHashMap<>(); // 순서대로 출력하기 위해 LinkedHashMap사용
	}// static 코드블럭 , 객체가 생성되기 전에 실행된다.
	
	// 싱글톤 패턴 만들어주기
	// 여러개의 객체가 만들어지더라고 객체의 상태가 없는 경우에 사용 가능 
	private static IDDITStudentDAO self = new DDITStudentDAO();
	private DDITStudentDAO(){}
	public static IDDITStudentDAO getInstance(){
		return self;
	}
	/* (non-Javadoc)
	 * @see kr.or.ddit.servlet03.dao.IDDITStudentDAO#insertStudent(kr.or.ddit.vo.DDITStudentVO)
	 */
	@Override
	public int insertStudent(DDITStudentVO vo){
//		s001,s002...
		int recordCnt = tableMap.size();
	    String code =  	String.format("S%3d", recordCnt+1);
		vo.setCode(code);
		tableMap.put(code, vo);		
		return 1; // 성공이면 1을  내보냄 
	
	}
	
	/* (non-Javadoc)
	 * @see kr.or.ddit.servlet03.dao.IDDITStudentDAO#selectAllStudents()
	 */
	@Override
	public List<DDITStudentVO> selectStudentList() {
		return new ArrayList<DDITStudentVO>( tableMap.values());
	}
	
	@Override
	public DDITStudentVO selectStudent(String code) {
		
		return null;
	}
	@Override
	public int updateStudent(DDITStudentVO vo) {
		
		return 0;
	}
	@Override
	public int deletStudent(String code) {
		
		
		return 0;
	}
}
