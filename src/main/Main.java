package main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import DB.SQLManager;
import DB.UserDBUtil;


public class Main {
	/**
	 * <p>Mainメソッド。現在はテスト用、使用方法の説明用として使用</p>
	 * @author 5415 土田,5409 坂本 雄一朗
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SQLManager.launchSQLManager();//SQLサーバーとの接続を確立
		
		/*
		 * 以下にコードを書く
		 */
		
		DBtest();///DB班のテスト用メソッド
	}
	
	public static void DBtest(){
		//ここで各種メソッドをテスト
		/*
		 * UserDBUtil
		 */
		//addUser()のテスト(ユーザの追加)
		//public static boolean addUser( String userId, String pw, String name )
		//UserDBUtil.addUser("5402","name2","name2");

		//removeUser()のテスト(ユーザの削除)
		//public static boolean removeUser( String userId ) {
		/*
		boolean result=UserDBUtil.removeUser("5402");
		System.out.print(result);
		*/

		//addPoint()のテスト(ユーザのポイント追加)
		//public static boolean addPoint( String userId, int value ) {
		//UserDBUtil.addPoint("pointtest",-10);
		
		//getAllUserID()
		//public static String[] getAllUserID() {
		String[] result=UserDBUtil.getAllUserID();
		for (String UserID :result)
		System.out.println(UserID);

		/*
		 * RequestDBUtil
		 */

		//setStatus()のテスト(依頼の状態を設定)
		//public static boolean setStatus( int requestId, int value ){
		//RequestDBUtil.setStatus(2,1);

		//setDetails()のテスト(内容の変更)
		//public static boolean setDetails( int requestId, String details ){
		/*
		boolean result=RequestDBUtil.setDetails(1,"誰かジュース買ってきて");
		System.out.print(result);
		*/

		//setDeadline()のテスト(依頼の締め切り日時を設定)
		//public static boolean setDeadline( int requestId, Date deadline )
		/*
		String strDate = "2018/03/02 01:23:45";
        System.out.println("設定日時= "+strDate);
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = sdFormat.parse(strDate);
		boolean result=RequestDBUtil.setDeadline(1, date);
		System.out.print(result);
		*/

		//setPoint()のテスト(ポイントの設定)
		//public static boolean setPoint( int requestId, int point ) {
		/*
		boolean result=RequestDBUtil.setPoint(1,100);
		System.out.print(result);
		*/

		//getRequest()のテスト(依頼の取得)
		//public static RequestRecord getRequest( int requestId ) {
		/*
		RequestRecord result = RequestDBUtil.getRequest(10);
		System.out.printf("requestID:%d\n",result.getRequestId());
		System.out.printf("name:%s\n",result.getName());
		System.out.printf("clientID:%s\n",result.getClientId());
		System.out.printf("clientName:%s\n",result.getClientName());
		System.out.printf("contractorID:%s\n",result.getContractorId());
		System.out.printf("contractorName:%s\n",result.getContractorName());
		System.out.printf("point:%d\n",result.getPoint());
		System.out.printf("advancePoint:%d\n",result.getAdpoint());
		System.out.printf("details:%s\n",result.getDetails());
		System.out.printf("status:%d\n",result.getStatus());
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.printf("deadline:%s\n",sdFormat.format(result.getDeadline()));
		*/

		//setContractorID()のテスト(受注者の設定)
		//public static boolean setContractorID( int requestId, String contractorID )
		/*
		boolean result=RequestDBUtil.setContractorID(1,"pointtest");
		System.out.print(result);
		*/

		//setRequestName()のテスト(依頼名の設定)
		//public static boolean setRequestName( int requestId, String name )
		/*
		boolean result=RequestDBUtil.setRequestName(1,"ジュース買ってきて");
		System.out.print(result);
		*/

		//getRequests()のテスト(依頼の取得(引数で返り値変化))
		//public static Pair[] getRequests( String clientUserId, String contractorUserId, int status)
		/*
		Pair[] rezults=RequestDBUtil.getRequests("testUserID","pointtest",1);
		for (Pair rezult: rezults){
		      System.out.printf("%s,%s\n",rezult.first,rezult.second);
		}
		*/

		//sample
		//DBへ接続するためのサンプルプログラム
		/*
		UserDBUtil.sample();
		*/


		/*
		 * Request
		 */
		//getMyOrdered()のテスト
		//public static ArrayList<RequestRecord> getMyOrdered(String userId) {
		/*
		System.out.print("ユーザーidを入力:");
		Scanner sc = new Scanner(System.in);
		String userId = sc.next();
		Request.getMyOrdered(userId);
		sc.close();
		*/
	}

}
