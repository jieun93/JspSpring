package kr.or.ddit.simple.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import kr.or.ddit.simple.OperatorType;

public class SimpleVO {
	
	@Min(Integer.MIN_VALUE)  // int 로 받을 수 최소값을 제한을 둘 수 있다.범위를 제한 
	@Max(Integer.MAX_VALUE)
	private int leftOp;
	private int rightOp;
	
//	private String operator;
	@NotNull
	private OperatorType operator;
	private double result;
	
//	public String getOperator() {
//		return operator;
//	}
//
//	public void setOperator(String operator) {
//		this.operator = operator;
//	}


	public OperatorType getOperator() {
		return operator;
	}

	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public int getLeftOp() {
		return leftOp;
	}

	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}

	public int getRightOp() {
		return rightOp;
	}

	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}

	public double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	
}
