package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

class AccountManager {
	
	public static void showMenu() {
		System.out.println("=== 원하시는 메뉴를 선택하세요. ===");
		System.out.println("");
		System.out.println(" 1.신규 계좌개설");
		System.out.print(" 2.입금   ");
		System.out.println("3.출금");
		System.out.println(" 4.개설된 계좌정보 출력 ");
		System.out.println(" 5.프로그램 종료");
		System.out.println("");
		System.out.println("============================");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager(100);
		
		boolean menuSelect = false;
		
		while(true) {
			try {		
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
						System.out.println("=== 전체 정보 출력이 완료되었습니다. ===");
						System.out.println("============================");
						break;
					case 5:
						System.out.println("프로그램 종료");
						return;
					}
				
				if(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
					menuSelect = true;
				} else {
					System.out.println("1부터 5까지의 숫자 중 하나를 선택하세요.");
				}
			} catch(InputMismatchException e) {
				System.out.println("메뉴는 숫자로만 입력가능합니다.");
				scan.nextLine();
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
	int accBalance, deposit, accInterest, balanceInt, accGradeRest, accFinalRest;
	
	public void makeAccount() {

		int choice = 0;
		boolean gradeSelect = false;
		
		while(!gradeSelect) {
			System.out.println("= 신규 계좌의 종류를 선택하세요. =");
			System.out.println("");
			System.out.println(" 1.보통 계좌");
			System.out.println(" 2.신용신뢰 계좌");
			System.out.println("");
			System.out.println("계좌 선택 : ");
			
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			
			if(choice == 1 || choice == 2) {
				gradeSelect = true;
			} else {
				System.out.println("정확한 종류를 선택하세요.");
			}
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호 : "); accNum = scan.nextLine();
		System.out.println("이름 : "); accName = scan.nextLine();
		System.out.println("입금 : "); accBalance = scan.nextInt();
		
		if(accBalance < 0) {
			System.out.println("입금액은 음수가 될 수 없습니다.");
		} else if (accBalance % 500 > 0) {
			System.out.println("입금은 500원 단위로만 가능합니다.");
		} else {	
			System.out.println("이율 : "); accInterest = scan.nextInt();
			scan.nextLine();

			if(choice == 1) {
				accType = "보통 계좌";
				accGrade = "없음";
				accGradeRest = 0;
			}
			
			if(choice == 2) {
				System.out.println("신용도 : "); accGrade = scan.nextLine();
			
				if(accGrade.equalsIgnoreCase("A")) {
					accType = "신용신뢰 계좌";
					accGradeRest = 7;
					System.out.println("추가 이율 : 7%");
				} else if(accGrade.equalsIgnoreCase("B")) {
					accType = "신용신뢰 계좌";
					accGradeRest = 4;
					System.out.println("추가 이율 : 4%");
				} else {
					accType = "신용신뢰 계좌";
					accGradeRest = 2;
					System.out.println("추가 이율 : 2%");
				}
			}

			accFinalRest = accInterest + accGradeRest;
			bankAccount[accountList++] = new HighCreditAccount(accNum, accName, accBalance, accInterest, accGrade, accType, accGradeRest, accFinalRest);
			
			showAccInfo();
			System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
			System.out.println("============================");
		}
	}
	public void depositMoney() {
			
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요 : ");
		String searchName = scan.nextLine();
		
		try {
			for(int i = 0 ; i<accountList ; i++) {
				
				balanceInt = (int) (bankAccount[i].balance * (accInterest + accGradeRest) / 100);
				
				if(searchName.compareTo(bankAccount[i].num)==0) {
						
					System.out.println("= 입력하신 계좌정보를 찾았습니다. =");
					
					bankAccount[i].showAccInfo();
					System.out.println("============================");
	
					System.out.println(" 입금액 : "); deposit = scan.nextInt();
					
					if(deposit < 0) {
						System.out.println("입금액은 음수가 될 수 없습니다.");
					} else if (deposit % 500 > 0) {
						System.out.println("입금은 500원 단위로만 가능합니다.");
					} else {
						System.out.println(" 입금액 : " + deposit);
						System.out.println(" 기존 잔액 : " + bankAccount[i].balance); 
						System.out.println(" 이자 : " + balanceInt); 
						System.out.println(" 최종 잔액 : "+(bankAccount[i].balance + balanceInt + deposit)+"원");
						System.out.println("============================");
						
						bankAccount[i].balance = bankAccount[i].balance + balanceInt + deposit;
					}
					isFind = true;
				}
			}
			if(isFind==false) {
				System.out.println("== 입력하신 계좌정보가 없습니다. ==");
				System.out.println("============================");
			}
		} catch(InputMismatchException e) {
			System.out.println("입금액은 숫자로만 입력가능합니다.");
		}
	}
	private int NormalAccount(int accountRest) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void withdrawMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요 : ");
		String searchName = scan.nextLine();

		try {
			for(int i = 0 ; i<accountList ; i++) {
				if(searchName.compareTo(bankAccount[i].num)==0) {
					System.out.println("= 입력하신 계좌정보를 찾았습니다. =");
	
					bankAccount[i].showAccInfo();
					System.out.println("============================");
					
					System.out.println("출금액 : "); deposit = scan.nextInt();
					
					if (bankAccount[i].balance - deposit < 0) {
						int choice = 0;
						boolean withdrawSelect = false;
						
						while(!withdrawSelect) {
							System.out.println("잔고가 부족합니다.");
							System.out.println("");
							System.out.println(" 1.잔액 모두 출금");
							System.out.println(" 2.출금 취소");
							System.out.println("");
							
							Scanner scan1 = new Scanner(System.in);
							choice = scan1.nextInt();
							
							if(choice == 1) {
								withdrawSelect = true;
								System.out.println("출금이 완료되었습니다.");
								System.out.println(" 잔액 : 0원");
								System.out.println("============================");
								bankAccount[i].balance = 0;		
							} else {
								System.out.println("출금이 취소되었습니다.");
								return;
							}
						}
					} else if(deposit < 0) {
						System.out.println("출금액은 음수가 될 수 없습니다.");
					} else if (deposit % 1000 > 0) {
						System.out.println("출금은 1000원 단위로만 가능합니다.");
					} else {					
						System.out.println(" 잔액 : "+(bankAccount[i].balance - deposit)+"원");
						System.out.println("============================");
						bankAccount[i].balance = bankAccount[i].balance - deposit;		
					}
					isFind = true;
				}
			}
			if(isFind==false) {
				System.out.println("== 입력하신 계좌정보가 없습니다. ==");
				System.out.println("============================");
			}
		} catch(InputMismatchException e) {
			System.out.println("출금액은 숫자로만 입력가능합니다.");
		}
	}
	public void showAccInfo() {
		for(int i = 0 ; i<accountList ; i++) {
			bankAccount[i].showAccInfo();
		}
	}
}