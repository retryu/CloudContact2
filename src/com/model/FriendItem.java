package com.model;

import com.db.model.Contact;
import com.db.model.Friend;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-6-24 下午01:44:49 file declare:
 */
public class FriendItem {
	private int type;
	private Friend friend;
	private Contact contact;
	private String  nickName;
	
	//1  friend  2contact
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
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "FriendItem [type=" + type + ", friend=" + friend + ", contact="
				+ contact + ", nickName=" + nickName + "]";
	}
	
	

}
