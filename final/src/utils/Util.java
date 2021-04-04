package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Util {
	/**
	 * 创建一个ID
	 * @return
	 */
	public static String createID(){
		//Java中日历类，处在util工具包中
		Calendar calendar=Calendar.getInstance();
		//获取年份
		 String year=String.valueOf(calendar.get(Calendar.YEAR));    
	     String month=String.valueOf(calendar.get(Calendar.MONTH)+1);    
	     String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));   
	     String hour=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) ; 
	     String min= String.valueOf( calendar.get(Calendar.MINUTE)); 
	     //再来一个随机数
	     Random rand=new Random();
	     String randStr=String.valueOf(rand.nextInt(9999)); //0-9999
	     
	     String id=year+month+day+hour+min+randStr;
	     
	     return id;
	}
	
	public static Date createNowDate(){
		 Date currentTime = new Date();
		 return currentTime;
	}
}
