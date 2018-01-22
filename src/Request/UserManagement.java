package Request;
import java.util.ArrayList;
import java.util.Date;

import DB.RequestDBUtil;
import DB.RequestRecord;
import DB.User;
import DB.UserDBUtil;

public class UserManagement {
	/**
	 * <p>ユーザの追加の実行とその可否を返すメソッド</p>
	* @author	5423和田幹広
	* @param	userId	ユーザID
	* @param	passwd	パスワード
	* @param	name	ユーザ名
	* @return	ユーザ追加の成功可否。
	*/
	public static boolean addUser(String userId, String passwd, String name){
		User newComer = UserDBUtil.getUser(userId);
		if(newComer == null)return false;
		return addUser(userId, passwd, name);
	}

	/**
	 * <p>依頼の追加の実行とその可否を返すメソッド</p>
	 * @author	5419 的野汰威
	 * @param	requestName 依頼名
	 * @param	point 報酬ポイント数
	 * @param	advancePoint デポジットポイント数
	 * @param	details 依頼の内容文
	 * @param	deadline 依頼の期限
	 * @return	登録成功の可否。
	*/
	public static boolean addQuest(String requestName, int point, int advancePoint, String details, Date deadline){
		RequestRecord ret = RequestDBUtil.addRequest(requestName, point, advancePoint, details, deadline);
		return (ret==null)?false:true;
	}

	/**
	 * <p>ログインの実行とその可否を返すメソッド</p>
	* @author	5422 湯浅智哉
	* @param	userid	ユーザID
	* @param	passwd	パスワード
	* @return	ログインの成功可否
	*/
	public static boolean login(String userid, String passwd){
		User u = UserDBUtil.login(userid, passwd);
		if(u != null)return true;
		else return false;
	}
	
	/**
	 * <p>全ユーザのインスタンスを取得するメソッド</p>
	* @author	5417成岡大輝
	* @return	全ユーザの情報が格納されたリスト。
	*/
	public static ArrayList<User> getUsersList(){
		String[] userIds = UserDBUtil.getAllUserID();// DBから全ユーザの情報を取得するメソッド。DB班の実装待ち。名称未定。
		ArrayList<User> ret = new ArrayList<User>();
		for(int i = 0; i < userIds.length; i++) {
			User u = UserDBUtil.getUser(userIds[i]);
			ret.add(u);
		}
		return ret;
	}
}
