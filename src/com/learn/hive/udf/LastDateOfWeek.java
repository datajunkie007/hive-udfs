package com.learn.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @author rphulari
 * UDF to get last date of the week
 */
public class LastDateOfWeek extends UDF {

	public String evaluate(String yyyyMMdd) throws Exception {
		return getLastDayOfWeek(yyyyMMdd);
	}

	public String getLastDayOfWeek(String yyyyMMdd) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(yyyyMMdd));

		if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.add(Calendar.DAY_OF_WEEK, 6);
		}

		return dateToStr(cal.getTime());
	}

	public Date strToDate(String yyyyMMdd) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(yyyyMMdd);
	}

	public String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		LastDateOfWeek dtw = new LastDateOfWeek();
		System.out.println(dtw.getLastDayOfWeek("2014-08-30"));
	}

}
