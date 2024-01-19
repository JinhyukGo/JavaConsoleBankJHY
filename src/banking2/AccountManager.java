package banking2;

import java.util.Scanner;

class AccountManager {
	
	public static void showMenu() {
		System.out.println("=== 원하시는 메뉴를 선택하세요. ===");
		System.out.println("");
		System.out.println(" 1.신규 계좌개설(신용도 A)");
		System.out.println(" 2.신규 계좌개설(신용도 B)");
		System.out.println(" 3.신규 계좌개설(신용도 C)");
		System.out.println(" 4.신규 계좌개설(신용도 D)");
		System.out.print(" 5.입금   ");
		System.out.println("6.출금");
		System.out.println(" 7.개설된 계좌 정보 출력 ");
		System.out.println(" 8.프로그램 종료");
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
				handler.makeAccountA();
				break;					
			case 2:
				handler.makeAccountB();
			break;					
			case 3:
				handler.makeAccountC();
			break;					
			case 4:
				handler.makeAccountD();
				break;					
			case 5:
				handler.depositMoney();
				break;
			case 6:
				handler.withdrawMoney();
				break;
			case 7:
				handler.showAccInfo();
				break;
			case 8:
				System.out.println("프로그램 종료");
				return;
			}
		}
	}

	private Account[] bankAccount;
	private int accountList;
	
	public AccountManager(int num) {
		bankAccount = new Account[num];
		accountList = 0;
	}
	
	String accNum, accName, accGrade, accType;
	int accBalance, deposit, accInterest;
	
	public void makeAccountA() {
		
		String accGrade = "A";
		String accType = "신용신뢰계좌";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("잔액 : "); accBalance = scan.nextInt();
		System.out.println("이율 : "); accInterest = scan.nextInt();
		System.out.println("신용도 : "+accGrade);
		System.out.println("계좌타입 : "+accType);
		
		
		bankAccount[accountList++] = new NormalAccount(accNum, accName, accBalance, accInterest, accGrade, accType);
		
		System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
		System.out.println("============================");
	}
	public void makeAccountB() {
		
		String accGrade = "B";
		String accType = "신용신뢰계좌";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("잔액 : "); accBalance = scan.nextInt();
		System.out.println("이율 : "); accInterest = scan.nextInt();
		System.out.println("신용도 : "+accGrade);
		System.out.println("계좌타입 : "+accType);
		
		bankAccount[accountList++] = new NormalAccount(accNum, accName, accBalance, accInterest, accGrade, accType);
		
		System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
		System.out.println("============================");
	}
	public void makeAccountC() {
		
		String accGrade = "C";
		String accType = "신용신뢰계좌";
				
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("잔액 : "); accBalance = scan.nextInt();
		System.out.println("이율 : "); accInterest = scan.nextInt();
		System.out.println("신용도 : "+accGrade);
		System.out.println("계좌타입 : "+accType);
		
		bankAccount[accountList++] = new NormalAccount(accNum, accName, accBalance, accInterest, accGrade, accType);
		
		System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
		System.out.println("============================");
	}
	public void makeAccountD() {
		
		String accGrade = "D";
		String accType = "보통예금계좌";
				
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("잔액 : "); accBalance = scan.nextInt();
		System.out.println("이율 : "); accInterest = scan.nextInt();
		System.out.println("신용도 : "+accGrade);
		System.out.println("계좌타입 : "+accType);
		
		bankAccount[accountList++] = new NormalAccount(accNum, accName, accBalance, accInterest, accGrade, accType);
		
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
				
				int accountRest;

				if(accGrade=="A") {
					accountRest = bankAccount[i].balance + bankAccount[i].balance*(accInterest + 7) + deposit;			
				} else if(accGrade=="B") {
					accountRest = bankAccount[i].balance + bankAccount[i].balance*(accInterest + 4) + deposit;		
				} else if(accGrade=="C") {
					accountRest = bankAccount[i].balance + bankAccount[i].balance*(accInterest + 2) + deposit;		
				} else if(accGrade=="D") {
					accountRest = bankAccount[i].balance + bankAccount[i].balance*accInterest + deposit;
				}
					
				System.out.println("= 입력하신 계좌정보를 찾았습니다. =");
				
				bankAccount[i].showAccInfo();
				System.out.println("============================");
				
				System.out.println("입금액 : "); deposit = scan.nextInt();
				System.out.println(" 잔액 : "+accountRest+"원");
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