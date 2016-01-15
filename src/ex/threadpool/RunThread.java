package ex.threadpool;

public class RunThread extends Thread{
		
	private boolean isShutdown;
	
	
	@Override
	public void run() {
		
		System.out.println("thread 실행...........");
		
		int i = 0;
		while (!isShutdown) {

			try {
				Thread.sleep(1000);
				System.out.println("thread 실행 중..."+(i++));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("thread 종료...........");

	}


	public boolean isShutdown() {
		return isShutdown;
	}


	public void setShutdown(boolean isShutdown) {
		this.isShutdown = isShutdown;
	}
		
		
		
}