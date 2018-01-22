package DB;
import java.util.List;
/**
 * <p>Userクラスは、それぞれのユーザーの情報をインスタンスに保持する。</p>
 *
 * @author 5415 土田 雄輝
 *
 */
public class User {

	private String name;
	private String userID;
	private int points;

	/**
	 * Userクラスのインスタンスを作成します。
	 * @param name ユーザの名前
	 * @param userID ユーザのID
	 * @param points ユーザのポイント
	 */
	public User(String name, String userID, int points){
		this.name = name;
		this.userID = userID;
		this.points = points;
	}
	
	public String getName(){
		return name;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public int getPoints(){
		return points;
	}
}
