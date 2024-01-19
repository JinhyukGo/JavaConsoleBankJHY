package banking1;

import java.util.Scanner;

public class Account {

	public static void showMenu() {
		System.out.println("=== 원하시는 메뉴를 선택하세요. ===");
		System.out.println("");
		System.out.println(" 1.신규 계좌개설    ");
		System.out.print(" 2.입금   ");
		System.out.println("3.출금");
		System.out.println(" 4.개설된 계좌 정보 출력 ");
		System.out.println(" 5.프로그램 종료");
		System.out.println("");
		System.out.println("============================");
	}

		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			AccountManager handler = new AccountManager();
			
			while(true) {
				showMenu();
				
				int choice = scan.nextInt();
				switch(choice) {
				case 1:
					handler.makeAccount();
					break;					
				case 2:
					handler.depositMoney();
					break;
				case 3:
					handler.withdrawMoney();
					break;
				case 4:
					handler.showAccInfo();
					break;
				case 5:
					System.out.println("프로그램 종료");
					return;
				}
			}
		}
	}

class AccountManager {
	
	private Account[] account;
	private int accountList;
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		
		String accNum, accName;
		int balance;
		
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("잔액 : "); balance = scan.nextInt();
		System.out.println("계좌 개설 정보 입력이 완료되었습니다.");
	}
	public void depositMoney() {
		int deposit;
		
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요 : ");
		String searchName = scan.nextLine();
		
		for(int i = 0 ; i<accountList ; i++) {
			if(searchName.compareTo(account[i].accNum)==0) {
				account[i].showAccInfo();
				System.out.println("** 입력하신 계좌정보를 찾았습니다. **");
				System.out.println("입금액 : "); deposit = scan.nextInt();
				System.out.println(" 잔액 : "+(balance+deposit)+"원");
				isFind = true;
			}
		}
		if(isFind==false) {
			System.out.println("** 입력하신 계좌정보와 일치하는 계좌가 없습니다. **");
		} // end of searchInfo
		
	}
	public void withdrawMoney() {
		
	}
	public void showAccInfo() {
		
	}
}

class Account {

}

