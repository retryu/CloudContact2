package com.model;

import com.db.model.Friend;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-18 ÏÂÎç12:57:53 file declare:
 */
public class FriendState {

	private String name;
	private int isAlreadyFriend;
	private String userId;
	private Friend friend;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsAlreadyFriend() {
		return isAlreadyFriend;
	}

	public void setIsAlreadyFriend(int isAlreadyFriend) {
		this.isAlreadyFriend = isAlreadyFriend;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

 
}
