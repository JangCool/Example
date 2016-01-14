package ex.threadpool;

import java.lang.management.ManagementFactory;

public class ThreadPool {

	/*
	 * 의문 addShutdownHook은 어떤 상황에서 작동하며 제대로 실행이 되는가?
	 * 
	 * Q1.main 메소드의 실행이 시작 된후 바로 종료 되는 경우.
	 *   - 프로그램 연산이 종료 됨과 동시에 바로 shutdownhook 실행
	 *   
	 * Q2.main 메소드의 실행이 시작 된 후 프로그램 구동 상태에서 Ctrl+C 버튼을 눌러 강제 종료 하는 경우.
	 *   - 콘솔 중간에 종료할것인지 묻는 동시에 Addshutdownhook 작동.
	 * 
	 * Q3.main 메소드의 실행이 시작 된 뒤 System.exit(0) 호출 하는 경우.
	 *   - 콘솔 중간에 종료할것인지 묻는 동시에 Addshutdownhook 작동.
	 * 
	 * Q4.main 메소드의 실행이 시작 된 뒤 shell 의 kill 명령어로 강제 종료 하는 경우.
	 *   - kill 명령어로 죽이면 addShutdownHook이 먹지 않고 jvm이 강제로 종료됨
	 * 
	 */
	
	public static void main(String[] args) {
		
		System.out.println("shutdown hook...... start...");
		System.out.println("Runtime PID => " + ManagementFactory.getRuntimeMXBean().getName());

		AddShutdownHook asdh = new AddShutdownHook();
		//Q1 START
		//asdh.addShutdownHookQ1();
		//Q1 END
				
		//Q2 or Q4 START
		asdh.addShutdownHookQ2();
		//Q2 or Q4 END
		
		//Q3 START
		//asdh.addShutdownHookQ3();
		//Q3 END
		
	}
}
