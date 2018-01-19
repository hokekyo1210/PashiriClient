package Request;
import java.util.Date;

import DB.RequestDBUtil;
import DB.RequestRecord;
import DB.User;
import DB.UserDBUtil;

public class UserManagement {
	/**
	* @author	5423和田幹広
	* @param	userId	ユーザID
	* @param	passwd	パスワード
	* @param	name	ユーザ名
	* @return	ユーザ追加の成功可否。
	*/
	public boolean addUser(String userId, String passwd, String name){
		User newComer = UserDBUtil.getUser(userId);
		if(newComer == null)return false;
		return addUser(userId, passwd, name);
	}

	/**
	 * @author	5419 的野汰威
	 * @param	requestName 依頼名
	 * @param	point 報酬ポイント数
	 * @param	advancePoint デポジットポイント数
	 * @param	details 依頼の内容文
	 * @param	deadline 依頼の期限
	 * @return	登録成功の可否。
	*/
	public boolean addQuest(String requestName, int point, int advancePoint, String details, Date deadline){
		RequestRecord ret = RequestDBUtil.addRequest(requestName, point, advancePoint, details, deadline);
		return (ret==null)?false:true;
	}
}
