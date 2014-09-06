package com.learn.hive.udf;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @author rphulari
 *
 * Returns age by for given Birthdate
 * birthday format : 2010-11-12 13:14:15
 */
public class GetAgeByBirthday extends UDF {

	public String evaluate(String yyyyMMdd) throws Exception {
		return getAge(yyyyMMdd);
	}

	public String getAge(String yyyyMMdd) throws Exception {
		if (StringUtils.isBlank(yyyyMMdd) || StringUtils.isEmpty(yyyyMMdd)) {
			return "0";
		} else {
			String year = yyyyMMdd;
			if (year.length() >= 10) {
				year = StringUtils.substring(yyyyMMdd, 0, 10);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = sdf.parse(year);

			Date today = new Date();

			long i = (today.getTime() - birthday.getTime())
					/ (1000 * 60 * 60 * 24);

			int age = (int) (i / 365);

			return Integer.valueOf(age).toString();
		}
	}

	public static void main(String[] args) throws Exception {
		GetAgeByBirthday ldm = new GetAgeByBirthday();
		System.out.println(ldm.getAge("1983"));
	}
}
