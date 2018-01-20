package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>MYSQLに接続し、引数に取ったクエリ文を実行するクラス</p>
 * @author 5415 土田,5409 坂本 雄一朗
 *
 */
public class SQLManager {

	private static final String serverName = "18.217.111.73";
	private static final String userName = "root";
	private static final String userPassword = "root";
	private static final String requestDBName = "requestDB";
	private static final String userDBName = "userDB";
	private static Connection requestConnection = null;
	private static Connection userConnection = null;

	/**
	 * <p>MYSQLとの接続を行うメソッド</p>
	 * @author 5415 土田,5409 坂本 雄一朗
	 * @throws Exception
	 */
	public static void launchSQLManager() throws Exception{
		Class.forName("org.sqlite.JDBC");
		requestConnection = DriverManager.getConnection("jdbc:mysql://"+serverName+"/"+requestDBName+"?autoReconnect=true&useSSL=false",userName,userPassword);
		userConnection = DriverManager.getConnection("jdbc:mysql://"+serverName+"/"+userDBName+"?autoReconnect=true&useSSL=false",userName,userPassword);
	}

	/**
	 * <p>requestDB(依頼データベース)へのSELECT文の実行を行うメソッド</p>
	 * @author 5415 土田,5409 坂本 雄一朗
	 * @param query(実行するクエリ文)
	 * @return 実行結果が入ったStatementオブジェクト
	 * @throws Exception
	 */
	public static ResultSet requestDBQuery(String query) throws Exception{
		Statement stmt = requestConnection.createStatement();
		return stmt.executeQuery(query);
	}

	/**
	 * <p>requestDB(依頼データベース)へのUPDATE,INSERT,DELETE文の実行を行うメソッド</p>
	 * @author 5415 土田,5409 坂本 雄一朗
	 * @param query(実行するクエリ文)
	 * @return 実行結果が入ったStatementオブジェクト
	 * @throws Exception
	 */
	public static int requestDBUpdate(String query) throws Exception{
		Statement stmt = requestConnection.createStatement();
		return stmt.executeUpdate(query);
	}

	/**
	 * <p>userDB(ユーザデータベース)へのSELECT文の実行を行うメソッド</p>
	 * @author 5415 土田,5409 坂本 雄一朗
	 * @param query(実行するクエリ文)
	 * @return 実行結果が入ったStatementオブジェクト
	 * @throws Exception
	 */
	public static ResultSet userDBQuery(String query) throws Exception{
		Statement stmt = userConnection.createStatement();
		return stmt.executeQuery(query);
	}


	/**
	 * <p>userDB(ユーザデータベース)へのUPDATE,INSERT,DELETE文の実行を行うメソッド</p>
	 * @author 5415 土田,5409 坂本 雄一朗
	 * @param query(実行するクエリ文)
	 * @return 実行結果が入ったStatementオブジェクト
	 * @throws Exception
	 */
	public static int userDBUpdate(String query) throws Exception{
		Statement stmt = userConnection.createStatement();
		return stmt.executeUpdate(query);
	}

}
