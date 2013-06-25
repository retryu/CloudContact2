package com.util;

import java.util.Comparator;


/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-6-24 上午11:23:15
 * file declare:
 */
public class PYCompare implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		 String str1 = PingYinUtil.getPingYin((String) o1);
	     String str2 = PingYinUtil.getPingYin((String) o2);
	     return str1.compareTo(str2);
	}

}
