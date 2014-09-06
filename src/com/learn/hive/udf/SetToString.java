package com.learn.hive.udf;

import java.util.List;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @author rphulari
 * UDF to convert set to string
 */
public class SetToString extends UDF {

	public String evaluate(Object set) throws Exception {
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) set;
		StringBuilder sb = new StringBuilder();

		for (String s : list) {
			sb.append(s).append(";");
		}

		String result = sb.toString();
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

}
