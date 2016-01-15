package ex.nightwork;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NightWorkCal {

	
	public static void main(String[] args) throws ParseException {

		
		int MAX_HOUR = 24;
		int NIGHTWORK_START_HOUR = 21;

		
		int diffDay = getDiffDay("20150114", "20150114");
		
		int startHour = 8;
		int startMin = 30;
		
		int endHour = 23;
		int endMin = 00;
		
	//	System.out.println(diffDay);
		
		//야근 시간.
		int workHour = 0;
		
		//당일 야근 등록 이라면.
		if(diffDay == 0){
			
			for (int i = startHour; i < MAX_HOUR; i++) {
				//평일일 경우 이시간 제외.
				if(i >= 9 && i <= NIGHTWORK_START_HOUR ){
					System.out.println(i);

					continue;
				}
				
				
				workHour++;

				if(endHour==i){
					break;
				}

			}
			
		}
		
		if((startMin+endMin) == 60) {
		//	workHour = workHour+1;
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
}
