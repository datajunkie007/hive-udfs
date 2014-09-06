package com.learn.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @author rphulari
 * UDF to get difference between months
 */
public class MonthDiff extends UDF {

	public String evaluate(String startDate, String endDate) throws Exception {

		if (startDate == null || endDate == null || "".equals(startDate)
				|| "".equals(endDate)) {
			return "1";
		}

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date end = sd.parse(endDate);
		Date start = sd.parse(startDate);

		long diff = end.getTime() - start.getTime();

		if (diff <= 0) {
			return "1";
		} else {
			Calendar calBegin = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			calBegin.setTime(start);
			calEnd.setTime(end);

			int m_begin = calBegin.get(Calendar.MONTH) + 1;
			int m_end = calEnd.get(Calendar.MONTH) + 1;
			// ??????????????????????????????
			int checkMonth = m_end - m_begin
					+ (calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR))
					* 12;

			if (checkMonth == 0) {
				checkMonth = 1;
			}
			return Integer.valueOf(checkMonth).toString();
		}
	}

	public static void main(String[] args) throws Exception {
		MonthDiff g = new MonthDiff();
		System.out.println(g.evaluate("2013-04-06 04:12:06",
				"2013-07-07 04:11:01"));
	}

}
