package com.db.model;

import com.db.dao.impl.MsgDaoImpl;
import com.db.dao.interfaze.MsgDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(daoClass = MsgDaoImpl.class)
public class Message {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String msgId;

	@DatabaseField
	private String msg_type;
	@DatabaseField
	private String msg;
	@DatabaseField
	private String data;
	@DatabaseField
	private boolean readed;
	@DatabaseField
	private String requestId;
	@DatabaseField
	private String request_user_name;

	@DatabaseField
	private String response_user_id;
	@DatabaseField
	private String response_user_name;
	@DatabaseField
	private String message_state;
	@DatabaseField
	private String content;

	@DatabaseField
	private String create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequest_user_name() {
		return request_user_name;
	}

	public void setRequest_user_name(String request_user_name) {
		this.request_user_name = request_user_name;
	}

	public String getResponse_user_id() {
		return response_user_id;
	}

	public void setResponse_user_id(String response_user_id) {
		this.response_user_id = response_user_id;
	}

	public String getResponse_user_name() {
		return response_user_name;
	}

	public void setResponse_user_name(String response_user_name) {
		this.response_user_name = response_user_name;
	}

	public String getMessage_state() {
		return message_state;
	}

	public void setMessage_state(String message_state) {
		this.message_state = message_state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", msgId=" + msgId + ", msg_type="
				+ msg_type + ", msg=" + msg + ", data=" + data + ", readed="
				+ readed + ", requestId=" + requestId + ", request_user_name="
				+ request_user_name + ", response_user_id=" + response_user_id
				+ ", response_user_name=" + response_user_name
				+ ", message_state=" + message_state + ", content=" + content
				+ ", create_time=" + create_time + "]";
	}

}
