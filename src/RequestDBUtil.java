
public class RequestDBUtil {
	/**
	 * <p>setDeadlineメソッドは、依頼IDに該当する依頼の期限を設定（更新）する。</p>
	 *
	 * @author 5402 越後 優太
	 *
	 * @param requestId 設定目標の依頼ID
	 * @param deadline 設定する期限
	 * @return 期限設定の成否
	 */
	public static boolean setDeadline( int requestId, Date deadline )	{
		// java.util.Date型からdatetime型の形式「yyyy-MM-dd HH:mm:ss」に整形
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String convDeadline = sdFormat.format( deadline );

		String query = "UPDATE Requests SET Deadline = cast('" + convDeadline + "' as Datetime ) WHERE RequestID = " + requestId;	//SQL文を生成

		try	{
		        SQLManager.requestDBUpdate( query );	//SQLManagerのメソッドを用いてクエリを飛ばす(この場合は依頼データベースに)
		}	catch (Exception e)	{
			      e.printStackTrace();
			      return false;	//クエリ送信失敗
		}
		return true;	//期限設定成功

		// 変換不要の場合(String型でdatetime型の形式「yyyy-MM-dd HH:mm:ss」で渡される)
		/*
		String query = "UPDATE Requests SET deadline = " + deadline + " WHERE RequestID = " + requestId;	//SQL文を生成
		System.out.println( query );
		try	{
			      ResultSet resultSet = SQLManager.requestDBQuery( query );	//SQLManagerのメソッドを用いてクエリを飛ばす(この場合は依頼データベースに)
		}	catch (Exception e)	{
			      e.printStackTrace();
			      return false;	//クエリ送信失敗
		}
		return true;	//期限設定成功
		*/
	}
}
