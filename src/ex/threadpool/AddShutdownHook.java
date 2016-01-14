package ex.threadpool;

public class AddShutdownHook {

	/**
	 *  Q1.main 메소드의 실행이 시작 된후 바로 종료 되는 경우.
	 *
	 */
	public void addShutdownHookQ1(){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("shutdown hook...... execute Q1");
			}
		});
	}
	
	/**
	 *  Q2.main 메소드의 실행이 시작 된 후 프로그램 구동 상태에서 Ctrl+C 버튼을 눌러 강제 종료 하는 경우.
	 *
	 */
	public void addShutdownHookQ2(){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("shutdown hook...... execute Q2");
			}
		});
		
		
		//shutdownHook을 등록 하고 프로그램 대기상태로 만들기 위해 sleep 호출.
		try {
			System.out.println("프로그램 구동 중......");
			Thread.sleep(50000000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  Q3.main 메소드의 실행이 시작 된 뒤 System.exit(0) 호출 하는 경우.
	 *
	 */
	public void addShutdownHookQ3(){
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("shutdown hook...... execute Q3");
			}
		});
		
		
		//shutdownHook을 등록 하고 프로그램 대기상태로 만들기 위해 sleep 호출.
		try {
			System.out.println("프로그램 구동 중......");
			Thread.sleep(5000);
			System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Q6. 규칙적으로 도는 Thread를 작동 시키고 addShutdownHook을 이용하여 thread를 종료시킬수 있나??
	 *
	 */
	public void addShutdownHookQ6(final RunThread runThread){
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				runThread.setShutdown(true);
				System.out.println("shutdown hook...... execute Q3");
			}
		});
		
		
		//shutdownHook을 등록 하고 프로그램 대기상태로 만들기 위해 sleep 호출.
		try {
			System.out.println("프로그램 구동 중......");
			Thread.sleep(5000);
			System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
