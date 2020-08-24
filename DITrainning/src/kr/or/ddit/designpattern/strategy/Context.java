package kr.or.ddit.designpattern.strategy;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class Context {
	
	private Sort strategy;
	
	@Resource(name="bubbleSort")
	public void setStrategy(Sort strategy) {
		this.strategy = strategy;
	}
	
	public void operation() {
		strategy.algorithm();
	}
}
