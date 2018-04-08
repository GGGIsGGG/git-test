package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	static long millisecondsOfOneDay = 1000*60*60*24;
	/**
	 * ʱ���ʽת��
	 * @param d ��Ҫת����ʱ�� util����ʽ��ʱ��
	 * @return  sql��ʽ��ʱ��
	 */
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	/**
	 * ��ȡ����
	 * @return
	 */
	public static Date today() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date monthBegin() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date monthEnd() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		
		return c.getTime();
	}
	
	public static int totalDayofMonth() {
		return (int)((monthEnd().getTime() - monthBegin().getTime()) / millisecondsOfOneDay) + 1;
	}
	
	public static int leftDayThisMonth() {
		return (int)((monthEnd().getTime() - today().getTime()) / millisecondsOfOneDay);
	}
	public static void main(String[] args) {
		System.out.println(leftDayThisMonth());
	}
}
