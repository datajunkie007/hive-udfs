package com.learn.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class GetSysDate extends UDF {

	public String evaluate() throws Exception {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(now);
	}
}
