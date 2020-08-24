package kr.or.ddit.commons.service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * @author PC-20
 * 인증 처리에 사용할 로직을 가진 Business Logic Layer(Service Layer)
 */
public interface IAuthenticateService {
	// db와 연결을 해서 인증을 확인 
		/**
		 * 인증로직
		 * @param membervo
		 * @return 존재여부 확인(notexist), 비번을 통한 인증 (ok. invalid,password)
		 */
		public ServiceResult authenticated(MemberVO member);
}
