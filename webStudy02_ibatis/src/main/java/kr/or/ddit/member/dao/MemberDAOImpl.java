package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO {
	// 싱글톤 만들기
	private MemberDAOImpl() {};
	private static IMemberDAO dao;
	public static IMemberDAO getInstance() {
		if(dao == null) dao = new MemberDAOImpl();
		return dao;
	}
	
	private SqlMapClient sql = CustomSqlMapClientBuilder.getSqlMapClient();
	
	
	/* (non-Javadoc)
	 * @see kr.or.ddit.member.dao.IMemberDAO#insertMember(kr.or.ddit.vo.MemberVO)
	 * 신규등록 회원
	 */
	@Override
	public int insertMember(MemberVO member) {
		try {
			return sql.update("MeberVO.insertMember", member);  // insert는 object pk키가 필요
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see kr.or.ddit.member.dao.IMemberDAO#selectMember(java.lang.String)
	 * 회원 정보 상세 조회
	 */
	@Override
	public MemberVO selectMember(String mem_id) {
		try {
			return (MemberVO) sql.queryForObject("Member.selectMember",mem_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see kr.or.ddit.member.dao.IMemberDAO#selelctMemberCount()
	 * 검색 조건에 맞는 회원수 조회
	 */
	@Override
	public int selelctMemberCount(PagingVO pagingVO) {
		try {
			return (Integer) sql.queryForObject("Member.selelctMemberCount",pagingVO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see kr.or.ddit.member.dao.IMemberDAO#selectMemberList()
	 * 검색 조건에 맞는 회원 목록 조회
	 */
	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try {
			return sql.queryForList("Member.selectMemberList",pagingVO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see kr.or.ddit.member.dao.IMemberDAO#updateMember(kr.or.ddit.vo.MemberVO)
	 * 회원 정보 수정
	 */
	@Override
	public int updateMember(MemberVO member) {
		try {
			return sql.update("Member.updateMember", member);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see kr.or.ddit.member.dao.IMemberDAO#deleteMember(java.lang.String)
	 * 회원 정보 삭제 
	 */
	@Override
	public int deleteMember(String	mem_id) {
		try {
			return sql.delete("Member.deleteMember", mem_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



}
