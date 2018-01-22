package CUI;

import java.util.ArrayList;
import java.util.Scanner;

import DB.RequestRecord;
import Request.RequestManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CUI {
	
	/*--5411 鈴木--*/
	public static void listUser() {
		ArrayList<RequestRecord> array = RequestManagement.getUsersList();
		for (RequestRecord user : array) {
		    System.out.print("ユーザID:"+user.getClientId());
		    System.out.println(" ユーザ名:"+user.getClientName());
		}
	}
	public static void listOrder() {
		ArrayList<RequestRecord> array = RequestManagement.getMyOrdering();
		for (RequestRecord request : array) {
			System.out.print("依頼ID:"+request.getRequestId());
		  	System.out.println(" 依頼名:"+request.getName());
		}

	}
	public static void listPoint() {
		ArrayList<RequestRecord> array = RequestManagement.getMyOrdered();
		for (RequestRecord request : array) {
			System.out.print("依頼ID:"+request.getRequestId());
			System.out.println(" 依頼名:"+request.getName());
		}
	}
	/*--鈴木 終わり--*/

	/*--5407 小島--*/
	public static void questDetail() {
	      System.out.printf("パシリ番号:");
				    Scanner scanner = new Scanner(System.in);
			        int number = scanner.nextInt();
			        ArrayList<RequestRecord> array = showOrder();
					for (RequestRecord request : array) {
					    System.out.println("依頼ID:"+request.getRequestId());
					    System.out.println("依頼名:"+request.getName());
					    System.out.println("発注者ID:"+request.getClientId());
					    System.out.println("受注者ID:"+request.getContractorId());
					    System.out.println("報酬ポイント:"+request.getPoint());
					    System.out.println("デポジット:"+request.getAdpoint());
					    System.out.println("内容:"+request.getDetails());
					    System.out.println("状態:"+request.getStatus());
					    //System.out.println("期限:"+RequestManagement.getDeadline());
	}
	public static void questRequest() {
	    String ID;
					System.out.print("受注する依頼ID：");
					BufferedReader buffer3 = new BufferedReader(new InputStreamReader(System.in));
					try {
						ID = buffer3.readLine();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					
				if(truepostOrder(userID,Id)){
					System.out.printf("受注成功\n");
					}else{
						System.out.printf("受注失敗\n");
					}
	}
	public static void questCancel() {
	    	String ID;
					System.out.print("受注を取り消す依頼ID：");
					BufferedReader buffer4 = new BufferedReader(new InputStreamReader(System.in));
					try {
						ID = buffer4.readLine();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					
				if(truecancelOrder(userID,Id)){
					System.out.printf("受注取り消し成功\n");
					}else{
						System.out.printf("受注取り消し失敗\n");
					}
	}
	/*--小島 終わり--*/

	/*--5406 郷地--*/
	public static void listRequest() {
		ArrayList<RequestRecord> array = RequestManagement.getAllOrder();
		for (RequestRecord record : array) {
		    System.out.println("依頼ID:"+record.getRequestId());
		    System.out.println("依頼名:"+record.getName());
		    System.out.println("報酬ポイント:"+record.getPoint());
		    System.out.println("-----");
		}
	}
	public static void addUser() {
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
	public static void addQuest() {
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
