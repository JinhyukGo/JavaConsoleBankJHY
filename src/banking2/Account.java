package banking2;

import java.util.Scanner;

class Bank {
	String num, name;
	int balance;
	
	public Bank(String num, String name, int balance) {
		this.num = num;
		this.name = name;
		this.balance = balance;
	}
	public void showAccInfo() {
		System.out.println("----------------------------");
		System.out.println("계좌번호 : " + num);
		System.out.println("이름 : " + name);
		System.out.println("잔액 : " + balance);
	}
}

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
		AccountManager handler = new AccountManager(100);
		
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

	public String accNum;

	public void showAccInfo() {
		// TODO Auto-generated method stub
		
	}
}

class AccountManager {
	
	private Bank[] bankAccount;
	private int accountList;
	
	public AccountManager(int num) {
		bankAccount = new Bank[num];
		accountList = 0;
	}
	
	String accNum, accName;
	int accBalance, deposit;
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("잔액 : "); accBalance = scan.nextInt();
		
		bankAccount[accountList++] = new Bank(accNum, accName, accBalance);
		
		System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
		System.out.println("============================");
	}
	public void depositMoney() {
		
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요 : ");
		String searchName = scan.nextLine();
		
		for(int i = 0 ; i<accountList ; i++) {
			if(searchName.compareTo(bankAccount[i].num)==0) {
				System.out.println("= 입력하신 계좌정보를 찾았습니다. =");
				
				bankAccount[i].showAccInfo();
				System.out.println("============================");
				
				System.out.println("입금액 : "); deposit = scan.nextInt();
				System.out.println(" 잔액 : "+(bankAccount[i].balance + deposit)+"원");
				System.out.println("============================");

				bankAccount[i].balance = bankAccount[i].balance + deposit;
				isFind = true;
			}
		}
		if(isFind==false) {
			System.out.println("== 입력하신 계좌정보가 없습니다. ==");
			System.out.println("============================");
		}
	}
	public void withdrawMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요 : ");
		String searchName = scan.nextLine();
		
		for(int i = 0 ; i<accountList ; i++) {
			if(searchName.compareTo(bankAccount[i].num)==0) {
				System.out.println("= 입력하신 계좌정보를 찾았습니다. =");

				bankAccount[i].showAccInfo();
				System.out.println("============================");
				
				System.out.println("출금액 : "); deposit = scan.nextInt();
				System.out.println(" 잔액 : "+(bankAccount[i].balance - deposit)+"원");
				System.out.println("============================");
				
				bankAccount[i].balance = bankAccount[i].balance - deposit;		
				isFind = true;
			}
		}
		if(isFind==false) {
			System.out.println("== 입력하신 계좌정보가 없습니다. ==");
			System.out.println("============================");
		}
	}
	public void showAccInfo() {
		for(int i = 0 ; i<accountList ; i++) {
			bankAccount[i].showAccInfo();
		}
		System.out.println("=== 전체 정보가 출력되었습니다. ===");
		System.out.println("============================");
	}
}