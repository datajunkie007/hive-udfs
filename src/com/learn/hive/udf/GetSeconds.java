package com.learn.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @author rphulari
 * Calculate seconds between max and min datetime
 */
public class GetSeconds extends UDF {

	public String evaluate(String endDate, String startDate) throws Exception {
		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)
				|| StringUtils.isBlank(startDate)
				|| StringUtils.isBlank(endDate)) {
			return "0";
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date end = sd.parse(endDate);
		Date start = sd.parse(startDate);

		long diff = end.getTime() - start.getTime();

		if (diff < 0) {
			return "0";
		} else if (diff == 0) {
			return "1";
		} else {
			long sec = diff / 1000;

			return Long.valueOf(sec).toString();
		}
	}
	
	public static void main(String[] args) throws Exception {
		GetSeconds g = new GetSeconds();
		System.out.println(g.evaluate("2013-04-06 04:12:06","2013-04-07 04:11:01"));
	}

}
