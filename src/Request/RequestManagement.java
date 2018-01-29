package Request;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import DB.Pair;
import DB.RequestDBUtil;
import DB.RequestRecord;
import DB.User;
import DB.UserDBUtil;

public class RequestManagement {

	/**
     * <p> ユーザIDを引数に、そのユーザが発注している依頼の一覧を取得するメソッド </p>
     * @author 5408 小林朝陽
     * @param userId ユーザID (String)
     * @return Pair配列 (依頼IDと依頼内容をまとめたクラスの配列, 失敗した場合はnull)
     */
    public static Pair<Integer,String>[] getMyOrdering(String userId) {
    	if (userId == null || userId == "") {
            System.out.println("error : ユーザIDが未入力(getMyOrdering)"); // デバッグ用
            return null;
        } else {
            return RequestDBUtil.getRequests(userId, null, -1);
        }
    }

    /**
	 *<p>ユーザIDを引数に、そのユーザが受注している依頼の一覧を取得するメソッド</p>
	 * @author 5401 打田将也
	 * @param userId:ユーザID
	 * @return 依頼IDと依頼名をまとめたPair(失敗した場合はnull)
	 */
	public static Pair<Integer,String>[] getMyOrdered(String userId) {
		if(userId == null || userId == "") {
			System.out.print("ユーザidを入力されていません");
			return null;
		}
		else {
			return RequestDBUtil.getRequests(null, userId, -1);
		}
	}

	/**
	 * <p>未受注の依頼をRequestRecord型のリストで返すメソッド</p>
	 * @author 5410 鈴木勇哉
	 * @return RequestRecordのArrayList
	 */
	public static ArrayList<RequestRecord> getAllYetOrder(){
		Pair<Integer,String>[] requests = RequestDBUtil.getRequests(null, "-1", -1);
		ArrayList <RequestRecord> ret = new ArrayList<RequestRecord>();
		for(int i = 0; i < requests.length; i++) {
			RequestRecord req = RequestDBUtil.getRequest(requests[i].first);
			ret.add(req);
		}
		return ret;
	}

	/**
     * <p>依頼IDを引数にそのIDの依頼の詳細情報を取得するメソッド</p>
     * @author 5414 張ニコラス謙豪
     * @param id: 依頼ID(int型)
     * @return RequestRecord{依頼ID(int)、依頼名(string)、発注者ID(string)、受注者ID(string(存在しない場合：-1))、報酬ポイント(int)、デポジット(int)、内容(string)、状態(int)、期限(Date)}
     */
    public static RequestRecord showOrder(int id){
    		if (id == -1){
            System.out.println("正しい依頼IDが入力されていません");
            return null;
        }else{
            return RequestDBUtil.getRequest(id);
        }
    }

    /**
	 * <p> 依頼の受注を行うメソッド </p>
	 * @author 5418藤野眞人
	 * @param	reqId 依頼ID
	 * @param	posterId 受注者ID
	 * @return 受注の成功の可否
	 */
	public static boolean postOrder(int reqId, String posterId){
		RequestRecord req = RequestDBUtil.getRequest(reqId);
		if(req.getRequestId() == -1)return false;
		return RequestDBUtil.setContractorID(reqId, posterId);
	}

	/**
	 * <p> 受注のキャンセルを行う </p>
	 * @author 5413 多田明貴子
	 * @param  userId	発注者id
	 * @param  reqId	依頼id
	 * @return 削除の可否
	 */
	public static boolean cancelOrder(String userId, int reqId){
	    RequestRecord req = RequestDBUtil.getRequest(reqId);
	    String clientId = req.getClientId();
	    if(userId.equals(clientId)){
	        return false;
	    }
	    return RequestDBUtil.removeRequest(reqId);
	}

	public static boolean addQuest(String requestName, String userID, int point, int advancePoint, String details, String deadline){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd_hh:mm:ss");
        
        try {
        		Date date = sdFormat.parse(deadline);
        		RequestRecord ret = RequestDBUtil.addRequest(requestName, point, advancePoint, details, date);
        		return true;
        }
        catch( ParseException pe ){
        		pe.printStackTrace();
        		return false;
        }
	}
/**
 * 実行結果のログ
 * -ログインしてください-
ID : matono
PASSWD : matono
ログイン成功
matono>add quest
依頼名:matono
依頼内容:aaaa
ポイント:100
手前ポイント:10
期限:2018/01/30_00:00:00
Insert into Requests(Name,Point,AdvancePoint,Details,Deadline) values('matono','100','10','aaaa', '2018-01-30 00:00:00' )
sql:SELECT * FROM Requests WHERE RequestID='16'
登録成功
matono
aaaa
100
10
2018/01/30_00:00:00
matono>list request
不明なコマンドです
matono>list order
Quest ID : -1 Quest Name : null
matono>list poss
不明なコマンドです
matono>poss
不明なコマンドです
matono>cancel
不明なコマンドです
matono>list user
SELECT UserID from Users
User ID : 5401 User name : name1
User ID : IDhoge User name : hoge
User ID : pointtest User name : test
User ID : testUserID User name : testName
matono>logout
不明なコマンドです
matono>login
不明なコマンドです
 * 
 */



}
