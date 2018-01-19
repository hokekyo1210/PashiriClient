package DB;
import java.sql.ResultSet;

public class UserDBUtil {

	/*
	 * ユーザの追加
	 */
	public static boolean addUser( String userId, String pw, String name ){
		   String query = "INSERT INTO Users(UserID,Name,Password) VALUES('"+userId+"','"+pw+"','"+name+"')";
		   // System.out.printf("sql:%s",query);
		   try {
		       int result =SQLManager.userDBUpdate(query);
		   } catch (Exception e) {
		       e.printStackTrace();
		       return false;  //クエリ送信失敗
		   }
		   return true;   //ユーザ追加成功
		}


	/*
	 * ユーザの削除
	 */

	public static boolean removeUser( String userId ) {
	   String query = "delete from Users where UserID = '" +userId+"'";
	   //System.out.printf("sql:%s",query);
	   try {
	       int result = SQLManager.userDBUpdate(query);

	       } catch (Exception e){
	           e.printStackTrace();
	           return false;  //クエリ送信失敗
	       }
	       return true;   //成功
	}


	/**
	 * <p>引数valueのポイントをuserIdのユーザに追加する</p>
	 * @author 5409 坂本 雄一朗
	 * @param userId
	 * @param value
	 * @return 成功:true,失敗:false
	 */
	public static boolean addPoint( String userId, int value ) {
		//get point
		String query = "SELECT point FROM Users where UserID='"+userId+"'";
		int point=0;
		int result=0;
		try {
			ResultSet resultSet =SQLManager.userDBQuery(query);
			if (resultSet.next()) {
				point=resultSet.getInt("point");
			}else {
				return false; //ユーザ名が無い
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;	//クエリ送信失敗
		}
		//add point
		point+=value;
		query = "UPDATE Users SET point = "+point+" where UserID='"+userId+"'";
		try {
			 result =SQLManager.userDBUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;	//クエリ送信失敗
		}
		return true;
	}

	/**
	 * <p>SQLManager内のメソッドを使用してSQL文を実行するためのサンプルメソッド</p>
	 * <p>ユーザデータベースへの追加・値取得、依頼データベースへの追加・値取得のテストプログラムが入っている</p>
	 * @author 5409 坂本 雄一朗
	 */
	public static boolean sample() {
		String query1 = "INSERT INTO Users(UserID,Name,Password) VALUES('testUserID','testName','testPassword')";
		String query2 = "SELECT * FROM Users";
		String query3 = "INSERT INTO Requests VALUES('1','testName','testClientID','testContractorID',0,0,'test',0,'2017-12-30 15:25:07')";
		String query4 = "SELECT * FROM Requests";
		try {
			//query1値追加
			System.out.printf("sql1:%s\n",query1);
			int result1 =SQLManager.userDBUpdate(query1);

			//query2値取得
			System.out.printf("sql2:%s\n",query2);
			ResultSet resultSet2 =SQLManager.userDBQuery(query2);
			System.out.printf("UserDB,Users table\n"
					+ "UserID\t\tName\t\tPassword\n");
			while(resultSet2.next()){
				System.out.printf("%s\t%s\t%s\n",resultSet2.getString("UserID"),resultSet2.getString("Name"),resultSet2.getString("Password"));
			}

			//query3値追加
			System.out.printf("sql3:%s\n",query3);
			int result3 =SQLManager.requestDBUpdate(query3);

			//query4値取得
			System.out.printf("sql4:%s\n",query4);
			ResultSet resultSet4 =SQLManager.requestDBQuery(query4);
			System.out.printf("requestDB, Requests table\n"
					+ "RequestID\tName\t\tClientID\tContractorID\t\tPoint\tAdvancePoint\tDetails\tStatus\tDeadline\n");
			while(resultSet4.next()){
				System.out.printf("%d\t\t%s\t%s\t%s\t%d\t%d\t\t%s\t%d\t%s\n",resultSet4.getInt("RequestID"),resultSet4.getString("Name"),resultSet4.getString("ClientID"),resultSet4.getString("ContractorID"),resultSet4.getInt("Point"),resultSet4.getInt("AdvancePoint"),resultSet4.getString("Details"),resultSet4.getInt("Status"),resultSet4.getString("Deadline"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;	//クエリ送信失敗
		}

		return true;	//ユーザ追加成功
	}
	/**
	 * <p>全ユーザIDを返すメソッド</p>
	 * @author 5415 土田 雄輝
	 * @return 全ユーザIDが格納されたString配列、失敗した場合はnull
	 */

	public static String[] getAllUserID() {
		String[] userIDs;
		int i=0;
		String query = "SELECT UserID from Users";
		try {
			System.out.println(query);
			ResultSet resultSet =SQLManager.userDBQuery(query);
			resultSet.last();
			int number_of_tables = resultSet.getRow();
			resultSet.beforeFirst();
			userIDs = new String[number_of_tables];
			while(resultSet.next()){
				userIDs[i]=resultSet.getString("UserID");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return (userIDs);
	}

	/*
	 * ログイン認証メソッド(未完成)
	 */
	public static User login( String userId, String pw ){
		return new User("taro jiro",userId,0);
	}

	/*
	 * ユーザインスタンスの取得(未完成)
	 */
	public static User getUser( String userId ){
		return new User("jiro taro 2",userId,0);
	}



}


