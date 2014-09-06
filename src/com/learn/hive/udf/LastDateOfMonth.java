package com.learn.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @author rphulari
 * UDF to get lastdate of the month
 */
public class LastDateOfMonth extends UDF {

	public String evaluate(String yyyyMMdd) throws Exception {
		return getLastDayOfMonth(yyyyMMdd);
	}

	public Date strToDate(String yyyyMMdd) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(yyyyMMdd);
	}

	public String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public String getLastDayOfMonth(String yyyyMMdd) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(strToDate(yyyyMMdd));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return dateToStr(cal.getTime());
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LastDateOfMonth ldm = new LastDateOfMonth();
		System.out.println(ldm.getLastDayOfMonth("2013-02-21"));
	}

}
