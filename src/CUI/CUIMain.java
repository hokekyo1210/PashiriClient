package CUI;

import java.util.ArrayList;
import java.util.Scanner;

import DB.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CUIMain {
	
	public User user;///ログイン中のユーザ
	
	public void main() {
		String cmd = null;
		
		//ユーザID
		String userID = "";
		
		// TODO 自動生成されたメソッド・スタブ
		System.out.printf("ログインしてください\n");
		CUI cui = new CUI();
	//	System.out.printf(menu_text);
		while(true){
		/*--5403 小田--*/
			String pass = null;
				BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
				try {
					userID = buffer.readLine();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
				BufferedReader buffer2 = new BufferedReader(new InputStreamReader(System.in));
				try {
					pass = buffer2.readLine();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			if(true/*login(userID,pass*/){
				System.out.printf("ログイン成功\n");
				break;
			}
		}
		/*--小田終わり--*/
			
		while(true){
			BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
			try {
				cmd = buffer.readLine();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		/*--5411 鈴木陸馬--*/
			if(cmd.equals("list user")){
				cui.listUser();
			}else if(cmd.equals("list order")){
				cui.listOrder(userID);
			}else if(cmd.equals("list post")){
				cui.listPost(userID);
		/*--鈴木終わり--*/

		/*--5407 小島空--*/
			}else if(cmd.equals("quest detail")){
				CUI.questDetail();
			}else if(cmd.equals("post request")){
				CUI.questRequest();
			}else if(cmd.equals("cancel request")){
				CUI.questCancel();
		/*--小島終わり--*/
        
		/*--5406 郷地素--*/
			}else if(cmd.equals("list request")){
				CUI.listRequest();
			}else if(cmd.equals("add user")){	
				CUI.addUser();
			}else if(cmd.equals("add quest")){
				CUI.addQuest();
			}else{
				System.out.println("不明なコマンドです");
			}
		/*--郷地終わり--*/
        
		}
	}

}
