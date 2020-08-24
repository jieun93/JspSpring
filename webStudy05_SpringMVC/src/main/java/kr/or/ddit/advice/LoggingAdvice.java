package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))") //point cut 중복되는거 따로 빼 놓음 
	public void dummy() {}
	
	@Around("dummy()")  // point cut
	public Object around(ProceedingJoinPoint  joinpoint) throws Throwable {
		String clzName = joinpoint.getTarget().getClass().getSimpleName(); //sampleservice impl
		Signature signature =  joinpoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinpoint.getArgs();
		
		
		
		logger.info("{}.{} 호출, 전달 파라미터 : {}",clzName, methodName, Arrays.toString(args));
		long startTime = System.currentTimeMillis();
		Object retValue = joinpoint.proceed(args);
		long endTime = System.currentTimeMillis();
			
		logger.info("{}.{} 호출 종료[{}ms], 반환값 : {}", clzName, methodName, (startTime-endTime), retValue);
		return retValue;
	}
	
	@Before("dummy()")
	public void before() {
		logger.info("핵심관심사 타켓 호출");
	}
	
}
