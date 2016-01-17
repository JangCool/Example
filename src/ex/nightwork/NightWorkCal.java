package ex.nightwork;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NightWorkCal {


	
	public static int MAX_HOUR = 24;
	public static int NIGHTWORK_START_HOUR = 21;
	public static int WORK_START_HOUR = 9;
	
	public static void main(String[] args) throws ParseException {
		
		


		String nightWorkSDay = "20150114";
		String nightWorkEDay = "20150116";
		
		int diffDay = getDiffDay(nightWorkSDay, nightWorkEDay);
		
		int startHour = 0;
		int startMin = 30;
		
		int endHour = 0;
		int endMin = 30;
		
	//	System.out.println(diffDay);
		
		//야근 시간.
		double workHour = 0;
		boolean isHoliday = false;
		
		//당일 야근 등록 이라면.
		if(diffDay == 0){			
			
			workHour = getNightWorkCnt(startHour, startMin, endHour, endMin,isHoliday);			
			
		}else if(diffDay > 0){
			
			String nightInputDay = null;

			for (int z = 0; z <= diffDay; z++) {

	
				
				if(z==0){
					nightInputDay = getNextDay(nightWorkSDay, z);	
					workHour = workHour+getNightWorkCnt(startHour, startMin, 0, 0,isHoliday);	
					System.out.println(z+","+getNightWorkCnt(startHour, startMin, 0, 0,isHoliday));

				}else if ((z+1) == diffDay) {
					nightInputDay = getNextDay(nightWorkSDay, (z+1));	
					workHour = workHour+getNightWorkCnt(0, 0, endHour, endMin,false);		
					System.out.println("isHoliday =>"+isHoliday);
					System.out.println(nightInputDay);
					System.out.println((z+1)+",((z+1)"+getNightWorkCnt(0, 0, endHour, endMin,false));

				}else{
					workHour = workHour+getNightWorkCnt(0, 0, 0, 0,true);	
					System.out.println("Zz :"+getNightWorkCnt(0, 0, 0, 0,true));


				}
				
				
				
				
			}
			
		}		
		
		System.out.println(workHour);

	}
	
	
	public static int getDiffDay(String startDate,String endDate) throws ParseException{
		
		
		Date sDate = new Date();
		Date eDate = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		sDate = df.parse(startDate);

		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		eDate = df1.parse(endDate);
		
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();


		startCal.setTime(sDate);
		endCal.setTime(eDate);




		long diffMillis = endCal.getTimeInMillis() - startCal.getTimeInMillis();

		int diffDay = (int) (diffMillis/(24 *60 * 60 *1000));
		
		return diffDay;
	}
	
	public static double getNightWorkCnt(int startHour,int startMin,int endHour,int endMin,boolean isHoliday) throws ParseException{
		
		double workHourCnt = 0.0;
		
//		if((startHour> endHour) || (startHour == endHour && startMin > endMin)){
//			System.out.println("시작시간이 종료시간 보다 큽니다.");
//			return 0.0;
//		}
//		
		if(startMin == 30 && endMin == 0){
			workHourCnt = workHourCnt-0.5;
		}			
		
		if(startMin == 0 && endMin == 30){
			workHourCnt = workHourCnt+0.5;
		}			


		if((startHour == 0 && startMin == 0 && endHour == 0 && endMin == 0) || (endHour == 0 && endMin == 0)){
			endHour = MAX_HOUR;
		}
		
		if( endHour >= WORK_START_HOUR && endHour < NIGHTWORK_START_HOUR && endMin ==30){
			endHour = endHour+1;
		}
		
		boolean isHalfMin = false;
		for (int i = startHour; i < endHour; i++) {
			
			if(!isHoliday){
					
				//평일일 경우 이시간 제외.
				if(i >= WORK_START_HOUR && i < NIGHTWORK_START_HOUR ){
					if(!isHalfMin){
												
						if(NIGHTWORK_START_HOUR > endHour && endMin == 30 ){
							workHourCnt = workHourCnt-0.5;
						}
						isHalfMin = true;
					}
					continue;
				}			

			}

			workHourCnt++;				

			if(i==24 && endMin == 00 ){
				break;
			}
				
		}
		
		return workHourCnt;
	}
	
	public static String getNextDay(String startDay,int amount) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
	    
	     java.util.Date dDate = new java.util.Date();
	     dDate = sdf.parse(startDay); //  <-- 20090901은 입력받은날짜
	      
	     Calendar cCal = Calendar.getInstance();
	     cCal.setTime(dDate);
	     
    	 cCal.add(Calendar.DATE,amount);
	     
		return sdf.format(cCal.getTime());
		
		
	}
}
