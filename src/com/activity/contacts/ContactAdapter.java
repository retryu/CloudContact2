package com.activity.contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.db.model.Contact;
import com.db.model.Friend;
import com.hengtiansoft.cloudcontact.R;
import com.model.FriendItem;
import com.util.PingYinUtil;
import com.util.PinyinComparator;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-6-24 上午10:37:46 file declare:
 */
class ContactAdapter extends BaseAdapter implements SectionIndexer {
	private Context mContext;
	private String[] mNicks;
	private List<FriendItem> friends;

	@SuppressWarnings("unchecked")
	public ContactAdapter(Context mContext) {
		this.mContext = mContext;
		// 排序(实现了中英文混排)

	}

	@Override
	public int getCount() {
		if (friends == null) {
			return 0;
		} else {
			return friends.size();
		}
	}

	@Override
	public Object getItem(int position) {
		return mNicks[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String nickName = "";

		FriendItem friendItem = friends.get(position);
		int type = friendItem.getType();

		if (type == 1) {
			Friend friend = friends.get(position).getFriend();
			if (friend != null) {
				nickName = friend.getName();
			}
		} else {
			Contact contact = friends.get(position).getContact();
			if (contact != null) {
				nickName = contact.getName();
			}
		}

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.contact_item, null);
			viewHolder = new ViewHolder();

			viewHolder.ivAvatar = (ImageView) convertView
					.findViewById(R.id.contactitem_avatar_iv);
			viewHolder.tvNick = (TextView) convertView
					.findViewById(R.id.contactitem_nick);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String catalog = "";
		// try {
		// catalog = converterToFirstSpell(PingYinUtil.getPingYin(nickName))
		// .substring(0, 1);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// if (position == 0) {
		// viewHolder.tvCatalog.setVisibility(View.VISIBLE);
		// viewHolder.tvCatalog.setText(catalog);
		// } else {
		// String lastCatalog = converterToFirstSpell(mNicks[position - 1])
		// .substring(0, 1);
		// if (catalog.equals(lastCatalog)) {
		// viewHolder.tvCatalog.setVisibility(View.GONE);
		// } else {
		// viewHolder.tvCatalog.setVisibility(View.VISIBLE);
		// viewHolder.tvCatalog.setText(catalog);
		// }
		// }

		viewHolder.ivAvatar.setImageResource(R.drawable.default_avatar);
		viewHolder.tvNick.setText(nickName);
		return convertView;
	}

	static class ViewHolder {
		ImageView ivAvatar;// 头像
		TextView tvNick;// 昵称
	}

	@Override
	public int getPositionForSection(int section) {
		char alpha;
		for (int i = 0; i < friends.size(); i++) {
			FriendItem friendItem = friends.get(i);
			String nick = friendItem.getNickName().toUpperCase();
			if (nick.length() >= 1) {
				alpha = nick.charAt(0);
				if (alpha == section) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	/**
	 * 昵称
	 */
	private static String[] nicks = { "阿雅", "阿a", "阿b", "阿c", "北a", "北b", "北c",
			"d1", "d2", "d3", "d4", "d5", "菜1", "菜2", "菜3", "菜4", "长a", "长b",
			"长c", "长d", "张山", "李四", "欧阳锋", "郭靖", "黄蓉", "杨过", "凤姐", "芙蓉姐姐",
			"移联网", "樱木花道", "风清扬", "张三丰", "梅超风" };

	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		chines = chines.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	public void setContacts(List<FriendItem> friendItems) {
		this.friends = friendItems;
		Collections.sort(friends, new PinyinComparator());
		System.out.println(friends);
		notifyDataSetChanged();
	}

	public List<FriendItem> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendItem> friends) {
		this.friends = friends;
	}
	
	

}
