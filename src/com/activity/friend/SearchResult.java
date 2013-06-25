package com.activity.friend;

import com.db.model.Friend;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-18 ÏÂÎç12:35:09 file declare:
 */
public class SearchResult {
	
	//0  for  not  found  1  alery is  friend  2 for friend's  content   
	private int type;
	private Friend friend;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}
	
	

}
