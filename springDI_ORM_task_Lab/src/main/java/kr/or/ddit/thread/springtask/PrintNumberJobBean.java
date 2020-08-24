package kr.or.ddit.thread.springtask;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class PrintNumberJobBean {

	private int number;
	
	
	@Scheduled(initialDelay = 0, fixedRate = 1000)
	public void printNumber() {
		System.out.printf("%s-%d\n",Thread.currentThread().getName(), ++number);
	}
	
	@Scheduled(cron = "*/2 * 3 * * MON") // (초, 분, 시, 날짜,  *, 요일)구체적인 시간ㅇ르 표현할수있다.  
	public void printDate() {
		System.err.printf("%s\n", new Date().toString());
		
	}
}
