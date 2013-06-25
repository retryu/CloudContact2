package com.util;

import java.util.Comparator;

import com.db.model.Contact;
import com.db.model.Friend;
import com.model.FriendItem;

public class PinyinComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		FriendItem friendItem1 = (FriendItem) o1;
		FriendItem friendItem2 = (FriendItem) o2;

		String str1 =friendItem1.getNickName();
		String str2 = friendItem2.getNickName();
		
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		str1 = str1.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
		str2 = str2.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
		return str1.compareTo(str2);
	}

}
