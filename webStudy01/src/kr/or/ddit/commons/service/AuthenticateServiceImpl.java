package kr.or.ddit.commons.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;

import kr.or.ddit.commons.dao.AuthenticateDAOlmpl;
import kr.or.ddit.commons.dao.IAuthenticateDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	// 싱글턴
	// dao와의 의존관계 형성 
	private AuthenticateServiceImpl() {	}
	private static IAuthenticateService self;
	public static IAuthenticateService getInstance() {
		if(self==null) self = new AuthenticateServiceImpl();
		return self;
	}
	
	// 의존관계 형성 
	IAuthenticateDAO dao = AuthenticateDAOlmpl.getInstance();
	
	@Override
	public ServiceResult authenticated(MemberVO member) {
		
	  MemberVO savedMember=dao.selectMember(member);
	  ServiceResult result = null;
	  if(savedMember!=null) {
		 try {
			 BeanUtils.copyProperties(member, savedMember);
		 }catch(IllegalAccessException | InvocationTargetException e){
			 throw new RuntimeException(e);
		 }
		 result = ServiceResult.OK;
	  }else {
		  result = ServiceResult.FAIL;
	  }
		return result;
	}

}
