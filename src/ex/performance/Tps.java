package ex.performance;

public class Tps {

	public static int DEFAULT_PERF_SIZE = 300;
	public static final int DEFAULT_MILISECOND = 1000;
	
	private Perf[] perf = new Perf[DEFAULT_PERF_SIZE];
	
	public int getPosition(){
		return (int) (System.currentTimeMillis()/DEFAULT_MILISECOND%DEFAULT_PERF_SIZE);
	}
	
	
	public Perf getPerf(){
		
		Perf p = null;
		
		try {
			p = perf[getPosition()];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	
	public synchronized void addValue(long sTime,long eTime){
		addValue(eTime - sTime);
	}
	
	public synchronized void addValue(long elapsed){
		
		Perf p = getPerf();
		
		if(p == null){
			p = new Perf();
		}
		
		p.elapsed += elapsed;
		p.count++;
		
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		long time1 = System.currentTimeMillis (); 
//		Thread.sleep(10000);
		long time2 = System.currentTimeMillis ();
		System.out.println ( ( time2 - time1 ) / 1000.0 );
		
		while (true) {
			Thread.sleep(1000);
			
	//		System.out.println(getPosition());
		}

	}
	
}
