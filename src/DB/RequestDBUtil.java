package DB;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>requestDB(依頼データベース)を操作するためのクラス</p>
 * @author 5414 土田
 *
 */
public class RequestDBUtil {

	/**
	 * <p>requestIdの依頼のステータスをvalueに変更する。</p>
	 * @author 5409 坂本 雄一朗
	 * @param requestId
	 * @param value
	 * @return 成功:true,失敗:false
	 */
	public static boolean setStatus( int requestId, int value ){
		String query = "UPDATE Requests SET Status = "+value+" where RequestID='"+requestId+"'";
		try {
			 int result =SQLManager.requestDBUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;	//クエリ送信失敗
		}
		return true;
	}

	/*
	 * 内容の設定
	 */
	public static boolean setDetails( int requestId, String details ){
    	String query = "UPDATE Requests SET Details = '"+details+"' where RequestID='"+requestId+"'";
   		//System.out.printf("sql:%s",query);
    	try {
   			  int result =SQLManager.requestDBUpdate(query);
   		 } catch (Exception e) {
   			 e.printStackTrace();
   			 return false;
   		 }
   		 return true;
    }

	/*
	 * 依頼の締切日時の追加
	 */
	public static boolean setDeadline( int requestId, Date deadline ){
		// java.util.Dateからdatetime型の形式「yyyy-MM-dd HH:mm:ss」に整形
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String convDeadline = sdFormat.format( deadline );

		String query = "UPDATE Requests SET Deadline = cast('" + convDeadline + "' as Datetime ) WHERE RequestID = " + requestId;	//SQL文を生成
		//System.out.println( query );
		try {
			SQLManager.requestDBUpdate( query );	//SQLManagerのメソッドを用いてクエリを飛ばす(この場合は依頼データベースに)
		} catch (Exception e) {
			e.printStackTrace();
			return false;	//クエリ送信失敗
		}
		return true; //期限設定成功
	}

	/*
	 * ポイントの設定
	 * 依頼テーブルのpointの値を変更
	 */
	public static boolean setPoint( int requestId, int point ) {
		String query = "UPDATE Requests SET Point = " + point + " WHERE RequestID = " + requestId;	//SQL文を生成
		try {
			SQLManager.requestDBUpdate( query );	//SQLManagerのメソッドを用いてクエリを飛ばす(この場合は依頼データベースに)
		} catch (Exception e) {
			e.printStackTrace();
			return false; //クエリ送信失敗
		}
		return true;
	}

	/*
	 * 依頼の取得
	 */
	public static RequestRecord getRequest( int requestId ) {
		String query = "SELECT * FROM Requests WHERE RequestID='"+requestId+"'";
		System.out.printf("sql:%s\n",query);
		RequestRecord rr = new RequestRecord();
		try {
			ResultSet rs = SQLManager.requestDBQuery(query);
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					rr.setRequestId(rs.getInt("RequestID"));
					rr.setName(rs.getString("Name"));
					rr.setClientId(rs.getString("ClientID"));
					rr.setContractorId(rs.getString("ContractorID"));
					rr.setPoint(rs.getInt("Point"));
					rr.setAdpoint(rs.getInt("AdvancePoint"));
					rr.setDetails(rs.getString("Details"));
					rr.setStatus(rs.getInt("Status"));
					String sdline = rs.getString("Deadline");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date dline = sdf.parse(sdline);
					rr.setDeadline(dline);
				}
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rr;
	}



	/*
	 * 受注者の設定
	 */
	public static boolean setContractorID( int requestId, String contractorID ) {
		String query = "UPDATE Requests SET ContractorID = '" + contractorID + "' WHERE RequestID = " + requestId;	//SQL文を生成
		//System.out.println( query );
		try {
			SQLManager.requestDBUpdate( query );	//SQLManagerのメソッドを用いてクエリを飛ばす(この場合は依頼データベースに)
		} catch (Exception e) {
			e.printStackTrace();
			return false; //クエリ送信失敗
		}
		return true;
	}

	/*
	 * 依頼名の設定
	 */
	public static boolean setRequestName( int requestId, String name ) {
		String query = "UPDATE Requests SET Name = '" + name + "' WHERE RequestID = " + requestId;	//SQL文を生成
		System.out.println( query );
		try {
			SQLManager.requestDBUpdate( query );	//SQLManagerのメソッドを用いてクエリを飛ばす(この場合は依頼データベースに)
		} catch (Exception e) {
			e.printStackTrace();
			return false; //クエリ送信失敗
		}
		return true;
	}
	/**
	 *
	 * <p>現在登録されている依頼の取得を行う。ただし、引数の内容によって絞り込み検索(ORで検索)が行える</P>
	 * @author 5409 坂本 雄一朗
	 * @param clientUserId
	 * @param contractorUserId
	 * @param status
	 * @return Pairの配列(該当する依頼がない場合と、エラーが発生した場合は-1,nullを入れて返す)
	 */
	public static Pair<Integer,String>[] getRequests( String clientUserId, String contractorUserId, int status){
		int i=0,flag=0;
		Pair<Integer,String> result_pairs[];

		String query = "SELECT RequestID,Name from Requests";
		if (clientUserId!=null) {
			query+=(flag==0)? " where " : " OR ";
			query+=" ClientID='"+clientUserId+"'";
			flag=1;
		}
		if (contractorUserId!=null) {
			query+=(flag==0)? " where " : " OR ";
			query+=" ContractorID='"+contractorUserId+"'";
			flag=1;
		}
		if (status!=-1) {
			query+=(flag==0)? " where " : " OR ";
			query+=" status="+status;
			flag=1;
		}
		//System.out.println(query);

		try {
			ResultSet resultSet =SQLManager.requestDBQuery(query);
			resultSet.last();
			if (resultSet.getRow()==0) {
				Pair<Integer,String> instsnce = new Pair<Integer, String>(-1,null);
				result_pairs = new Pair[1];
				result_pairs[0]=instsnce;
				return (result_pairs);
			}else {
				result_pairs = new Pair[resultSet.getRow()];
			}
			System.out.print(resultSet.getRow());
			resultSet.beforeFirst();

			while(resultSet.next()){
				Pair<Integer,String> instsnce = new Pair<Integer, String>(resultSet.getInt("RequestID"),resultSet.getString("Name"));
				result_pairs[i]=instsnce;
				i++;
			}
			return (result_pairs);
		} catch (Exception e) {
			e.printStackTrace();
			Pair<Integer,String> instsnce = new Pair<Integer, String>(-1,null);
			result_pairs = new Pair[1];
			result_pairs[0]=instsnce;
			return (result_pairs);	//クエリ送信失敗

		}
	}
	

	/*
	 * 依頼の追加(未完成)
	 */
	public static RequestRecord addRequest( String requestName, int point , int advancePoint, String details, Date deadline  ){
		return new RequestRecord();
	}
	
	/*
	 * 依頼の削除(未完成)
	 */
	public static boolean removeRequest( int requestId ){
		return true;
	}
}
