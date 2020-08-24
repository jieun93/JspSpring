package kr.or.ddit.simple.service;

import javax.inject.Inject;

import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Service;

import kr.or.ddit.HomeController;
import kr.or.ddit.simple.vo.SimpleVO;

@Service // 빈 등록
public class SimpleService {
	
//	// 주입하기 --> 생성자, 셋터 필요 
//	private HomeController home;
//	
//	@Inject
//	public void setHome(HomeController home) {
//		this.home = home;
//	}
	
//	public void  plus(SimpleVO simple) {
//		  int leftOp = simple.getLeftOp();
//		  int rightOp = simple.getRightOp();
//		  int result = leftOp + rightOp;
//		  simple.setResult(result);
//	}
	
//	public void clac(SimpleVO simple) {
//		int leftOp = simple.getLeftOp();
//		int rightOp = simple.getRightOp();
//		double result = 0;
//		
//		switch (simple.getOperator()) {
//		case "PLUS":
//			 result = leftOp+rightOp;
//			break;
//		
//		case "MINUS":
//			result = leftOp - rightOp;
//			break;
//		case "MUTIPLY" :
//			result = leftOp * rightOp;
//			break;
//		case "DIVIED" :
//			result = leftOp / rightOp;
//			break;
//		
//		}
//		simple.setResult(result);
//		
//	}
//	
	
	public void plus(SimpleVO simple) {
		int leftOp = simple.getLeftOp();
		int rightOp = simple.getRightOp();
		int result = (int) simple.getOperator().operate(leftOp, rightOp);
		simple.setResult(result);
	}
	
	
	
}
