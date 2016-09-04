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
	
	public double getTps(int second){
		
		int currPosition = getPosition();
			
		int count = 0;
		long elapsed = 0;
		
		//second는 현재 값부터 계속 --(차감)을 해주고 0이되면 while루프를 빠져나오게 한다.
		//만약 second 값이 4라면 배열 위치는 3을 가르키기 때문에 second가 1이면 배열은 0일 가리키게 된다. second가 0이면 ArrayIndexOutBoundException에러가 발생 하니 주의한다.
		while (second > 0) {
			System.out.println(currPosition);
			Perf tmp = perf[currPosition];
			
			count += tmp.count;
			elapsed += tmp.elapsed;

			
			
			
			
			second--;
			currPosition--;

			//현재 포지션이 0일 경우 배열 최대값인 299값으로 설정한다. size크기는 300개(초)지만 배열은 0부터 시작하기에 -1을 차감하여 299에서 시작한다.
			//0 이후에서 currPosition -- 계산을 하면 음수로 계산되기 때문에 그전에 변경해 주어야한다.
			if(currPosition == 0){
				currPosition = DEFAULT_PERF_SIZE-1;
			}
			
		}

		return 0.0;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
//		Thread.sleep(10000);
		int[] second =  new int[]{1000,2000,3000,4000,5000};
		
		Tps tps = new Tps();
		tps.getTps(4);

		
//		int i = 30;
//		
//		while (true) {
//
//			long time1 = System.currentTimeMillis (); 
//
//			Thread.sleep(Math.round(Math.random()*second.length));
//
//			long time2 = System.currentTimeMillis ();
//			
//			tps.addValue(time2 - time1);
//
//			i++;
//
//			tps.getTps(100);
//
//		}
		

	}
	
}
