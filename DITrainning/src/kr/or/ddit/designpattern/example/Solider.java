package kr.or.ddit.designpattern.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import kr.or.ddit.designpattern.adapter.example.XiaomiProduct;

@Component
public class Solider {
	
	private XiaomiProduct xiaomi; //has a  관계
	@Inject
	public void setXiami(XiaomiProduct xiami) {
		this.xiaomi = xiaomi;
	

	}
	public void getXiami() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	@PostConstruct   // xml에 직접 설정하지 않아도 라이프사이클을 지정할 수 있다.
	public void init() {
		System.out.println("객체 생성");
	}
	
	@PreDestroy // xml에 직접 설정하지 않아도 라이프사이클을 지정할 수 있다. 
	public void destroy() {
		System.out.println("객체 소멸");
	}
	
	
//	@Resource(name = "biBiTan") // resource : 시작하자마자 실행하는거 
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	private Gun gun;
	
	
//	@Inject
	@Resource(name="biBiTan")
	public void armedWithGun(Gun gun) {
		this.gun = gun;
	}
	
	public void attack() {
		gun.shot();
	}
}
