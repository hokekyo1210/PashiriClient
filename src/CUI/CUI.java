package CUI;

import java.util.ArrayList;
import java.util.Scanner;

import DB.Pair;
import DB.RequestRecord;
import DB.User;
import DB.UserDBUtil;
import Request.RequestManagement;
import Request.UserManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CUI {
	
	/*--5411 鈴木--*/
	public void listUser(){
		ArrayList<User> array = UserManagement.getUsersList();
		for(int i = 0; i < array.size(); i++){
			System.out.print("User ID : " + array.get(i).getUserID());
			System.out.println(" User name : "+array.get(i).getName());
		}
	}
//	public static void listUser() {
//		ArrayList<User> array = RequestManagement.getUsersList();
//		for (RequestRecord user : array) {
//		    System.out.print("ユーザID:"+user.getClientId());
//		    System.out.println(" ユーザ名:"+user.getClientName());
//		}
//	}
	public void listOrder(String userID){
		Pair<Integer, String>[] pairs = RequestManagement.getMyOrdering(userID);
		for(int i = 0; i < pairs.length; i++){
			System.out.print("Quest ID : "+ pairs[i].first);
			System.out.println(" Quest Name : "+ pairs[i].second);
		}
	}
//	public static void listOrder() {
//		ArrayList<RequestRecord> array = RequestManagement.getMyOrdering();
//		for (RequestRecord request : array) {
//			System.out.print("依頼ID:"+request.getRequestId());
//		  	System.out.println(" 依頼名:"+request.getName());
//		}
//
//	}
	
	public void listPost(String userID){
		Pair<Integer, String>[] pairs = RequestManagement.getMyOrdered(userID);
		for(int i = 0; i < pairs.length; i++){
			System.out.print("Quest ID : "+ pairs[i].first);
			System.out.println(" Quest Name : "+ pairs[i].second);
		}
	}
//	public static void listPoint() {
//		ArrayList<RequestRecord> array = RequestManagement.getMyOrdered();
//		for (RequestRecord request : array) {
//			System.out.print("依頼ID:"+request.getRequestId());
//			System.out.println(" 依頼名:"+request.getName());
//		}
//	}
	/*--鈴木 終わり--*/

	/*--5407 小島--*/
	public void questDetail() {
		System.out.printf("パシリ番号:");
	    Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        RequestRecord request = RequestManagement.showOrder(number);
	    System.out.println("依頼ID:"+request.getRequestId());
	    System.out.println("依頼名:"+request.getName());
	    System.out.println("発注者ID:"+request.getClientId());
	    System.out.println("受注者ID:"+request.getContractorId());
	    System.out.println("報酬ポイント:"+request.getPoint());
	    System.out.println("デポジット:"+request.getAdpoint());
	    System.out.println("内容:"+request.getDetails());
	    System.out.println("状態:"+request.getStatus());
	    scanner.close();
	}
	
	public void questRequest(String userID) {
		System.out.print("受注する依頼ID：");
	    Scanner scanner = new Scanner(System.in);
        int ID = scanner.nextInt();
        scanner.close();
		if(RequestManagement.postOrder(ID, userID)){
			System.out.printf("受注成功\n");
		}else{
			System.out.printf("受注失敗\n");
		}
	}
	public void questCancel(String userID) {
		    Scanner scanner = new Scanner(System.in);
	        int ID = scanner.nextInt();
	        scanner.close();
	        if(RequestManagement.cancelOrder(userID, ID)){
	        	System.out.printf("受注取り消し成功\n");
			}else{
				System.out.printf("受注取り消し失敗\n");
			}
	}
	/*--小島 終わり--*/


	/*--5406 郷地--*/
	/*すべての依頼を出力するメソッドが存在しない*/
//	public static void listRequest() {
//		ArrayList<RequestRecord> array = RequestManagement.getAllOrder();
//		for (RequestRecord record : array) {
//		    System.out.println("依頼ID:"+record.getRequestId());
//		    System.out.println("依頼名:"+record.getName());
//		    System.out.println("報酬ポイント:"+record.getPoint());
//		    System.out.println("-----");
//		}
//	}
//	
	
	
	public void addUser() {
		BufferedReader buffer = null;
		//ユーザー登録変数
		String add_ID = null;
		String add_name = null;
		String add_pass = null;
		
		try {
			System.out.print("名前:");
			add_name = buffer.readLine();
			System.out.print("ID:");
			add_ID = buffer.readLine();
			System.out.print("パスワード:");
			add_pass = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(UserDBUtil.addUser(add_ID,add_pass,add_name)){
			System.out.println("登録成功");
		} else {
			System.out.println("登録失敗");
		}
	}
	public void addQuest(String userID) {
		BufferedReader buffer = null;	
		//追加依頼についての変数
		String request_name = null;	//依頼名
		String contents = null;	//内容
		int point = 0;	//ポイント
		int deposit =0;	//手前ポイント
		String date = null;	//期限
		try {
			System.out.print("依頼名:");
			request_name = buffer.readLine();
			System.out.print("依頼内容:");
			contents = buffer.readLine();
			System.out.print("ポイント:");
			Scanner scanner1 = new Scanner(System.in);
			point = scanner1.nextInt();
			System.out.print("手前ポイント:");
			Scanner scanner2 = new Scanner(System.in);
			deposit = scanner2.nextInt();
			System.out.print("期限:");
			date = buffer.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(RequestManagement.addQuest(request_name,userID,point,deposit,contents,date)){
			System.out.println("登録成功");
			System.out.println(request_name);
			System.out.println(contents);
			System.out.println(point);
			System.out.println(deposit);
			System.out.println(date);	
		} else {
			System.out.println("登録失敗");
		}
	}
	/*--郷地 終わり--*/
}
