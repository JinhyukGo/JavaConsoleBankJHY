package banking5;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

class AccountManager {
	
	public static void showMenu() {
		System.out.println("=== 원하시는 메뉴를 선택하세요. ===");
		System.out.println("");
		System.out.println(" 1.신규 계좌개설");
		System.out.print(" 2.입금   ");
		System.out.println("3.출금");
		System.out.println(" 4.기존 계좌 삭제 ");
		System.out.println(" 5.개설된 계좌정보 출력 ");
		System.out.println(" 6.프로그램 종료");
		System.out.println("");
		System.out.println("============================");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager();
		
		boolean menuSelect = false;
		
		handler.readAccount();
		
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
						handler.deleteAccount();
						break;
					case 5:
						handler.showAccInfo();
						System.out.println("= 전체 정보 출력이 완료되었습니다. =");
						System.out.println("============================");
						break;
					case 6:
						handler.saveAccount();
						System.out.println("=== 프로그램이 종료되었습니다. ===");
						System.out.println("============================");
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

	HashSet<Account> bankAccount;
	
	public AccountManager() {
		bankAccount = new HashSet<Account>();
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

				NormalAccount normal = new NormalAccount(accNum, accName, accBalance, accInterest, accGrade, accType);
				boolean nCheck = bankAccount.add(normal);
				
				if(nCheck == false) {
					System.out.println("= 입력하신 계좌정보와 동일한 계좌를 찾았습니다. =");
					for(Account acc : bankAccount) {
						if(accNum.compareTo(acc.num)==0) {
							acc.showAccInfo();
							System.out.println("============================");
						}
					}
					
					int choice2 = 0;
					boolean makeSelect = false;
					
					while(!makeSelect) {
						System.out.println("= 원하시는 메뉴를 선택하세요. =");
						System.out.println("");
						System.out.println(" 1.기존 계좌에 덮어쓰기");
						System.out.println(" 2.기존 계좌 유지 후 종료");
						System.out.println("");
						System.out.println("메뉴 선택 : ");
						
						Scanner scan2 = new Scanner(System.in);
						choice2 = scan2.nextInt();
						
						if(choice2 == 1) {
							bankAccount.remove(normal);
							bankAccount.add(normal);
							System.out.println("= 기존 계좌 덮어쓰기가 완료되었습니다. =");
							System.out.println("============================");
							return;
						} else if(choice2 == 2) {
							System.out.println("= 신규 계좌 개설이 종료되었습니다. =");
							System.out.println("============================");	
							return;
						} else {
							System.out.println("정확한 메뉴를 선택하세요.");						
						}
					}			
				} else if(nCheck == true) {
					System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
					System.out.println("============================");
				}	
			}
		
			if(choice == 2) {
				System.out.println("신용도 : "); accGrade = scan.nextLine();
			
				if(accGrade.equalsIgnoreCase("A")) {
					accType = "신용신뢰 계좌";
					accGradeRest = 7;
					System.out.println("추가 이율 : 7%");
					
					accFinalRest = accInterest + accGradeRest;					
					HighCreditAccount high = new HighCreditAccount(accNum, accName, accBalance, accInterest, accGrade, accType, accGradeRest, accFinalRest);
					boolean nCheck = bankAccount.add(high);
					
					if(nCheck == false) {
						System.out.println("= 입력하신 계좌정보와 동일한 계좌를 찾았습니다. =");
						for(Account acc : bankAccount) {
							if(accNum.compareTo(acc.num)==0) {
								acc.showAccInfo();
								System.out.println("============================");
							}
						}
						
						int choice2 = 0;
						boolean makeSelect = false;
						
						while(!makeSelect) {
							System.out.println("= 원하시는 메뉴를 선택하세요. =");
							System.out.println("");
							System.out.println(" 1.기존 계좌에 덮어쓰기");
							System.out.println(" 2.기존 계좌 유지 후 종료");
							System.out.println("");
							System.out.println("메뉴 선택 : ");
							
							Scanner scan2 = new Scanner(System.in);
							choice2 = scan2.nextInt();
							
							if(choice2 == 1) {
								bankAccount.remove(high);
								bankAccount.add(high);
								System.out.println("= 기존 계좌 덮어쓰기가 완료되었습니다. =");
								System.out.println("============================");
								return;
							} else if(choice2 == 2) {
								System.out.println("= 신규 계좌 개설이 종료되었습니다. =");
								System.out.println("============================");	
								return;
							} else {
								System.out.println("정확한 메뉴를 선택하세요.");						
							}
						}			
					} else if(nCheck == true) {
						System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
						System.out.println("============================");
					}
				} else if(accGrade.equalsIgnoreCase("B")) {
					accType = "신용신뢰 계좌";
					accGradeRest = 4;
					System.out.println("추가 이율 : 4%");
					
					accFinalRest = accInterest + accGradeRest;					
					HighCreditAccount high = new HighCreditAccount(accNum, accName, accBalance, accInterest, accGrade, accType, accGradeRest, accFinalRest);
					boolean nCheck = bankAccount.add(high);
					
					if(nCheck == false) {
						System.out.println("= 입력하신 계좌정보와 동일한 계좌를 찾았습니다. =");
						for(Account acc : bankAccount) {
							if(accNum.compareTo(acc.num)==0) {
								acc.showAccInfo();
								System.out.println("============================");
							}
						}
						
						int choice2 = 0;
						boolean makeSelect = false;
						
						while(!makeSelect) {
							System.out.println("= 원하시는 메뉴를 선택하세요. =");
							System.out.println("");
							System.out.println(" 1.기존 계좌에 덮어쓰기");
							System.out.println(" 2.기존 계좌 유지 후 종료");
							System.out.println("");
							System.out.println("메뉴 선택 : ");
							
							Scanner scan2 = new Scanner(System.in);
							choice2 = scan2.nextInt();
							
							if(choice2 == 1) {
								bankAccount.remove(high);
								bankAccount.add(high);
								System.out.println("= 기존 계좌 덮어쓰기가 완료되었습니다. =");
								System.out.println("============================");
								return;
							} else if(choice2 == 2) {
								System.out.println("= 신규 계좌 개설이 종료되었습니다. =");
								System.out.println("============================");	
								return;
							} else {
								System.out.println("정확한 메뉴를 선택하세요.");						
							}
						}			
					} else if(nCheck == true) {
						System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
						System.out.println("============================");
					}
				} else if(accGrade.equalsIgnoreCase("C")) {
					accType = "신용신뢰 계좌";
					accGradeRest = 2;
					System.out.println("추가 이율 : 2%");
					
					accFinalRest = accInterest + accGradeRest;
					HighCreditAccount high = new HighCreditAccount(accNum, accName, accBalance, accInterest, accGrade, accType, accGradeRest, accFinalRest);
					boolean nCheck = bankAccount.add(high);
					
					if(nCheck == false) {
						System.out.println("= 입력하신 계좌정보와 동일한 계좌를 찾았습니다. =");
						for(Account acc : bankAccount) {
							if(accNum.compareTo(acc.num)==0) {
								acc.showAccInfo();
								System.out.println("============================");
							}
						}
						
						int choice2 = 0;
						boolean makeSelect = false;
						
						while(!makeSelect) {
							System.out.println("= 원하시는 메뉴를 선택하세요. =");
							System.out.println("");
							System.out.println(" 1.기존 계좌에 덮어쓰기");
							System.out.println(" 2.기존 계좌 유지 후 종료");
							System.out.println("");
							System.out.println("메뉴 선택 : ");
							
							Scanner scan2 = new Scanner(System.in);
							choice2 = scan2.nextInt();
							
							if(choice2 == 1) {
								bankAccount.remove(high);
								bankAccount.add(high);
								System.out.println("= 기존 계좌 덮어쓰기가 완료되었습니다. =");
								System.out.println("============================");
								return;
							} else if(choice2 == 2) {
								System.out.println("= 신규 계좌 개설이 종료되었습니다. =");
								System.out.println("============================");	
								return;
							} else {
								System.out.println("정확한 메뉴를 선택하세요.");						
							}
						}			
					} else if(nCheck == true) {
						System.out.println("= 신규 계좌 개설이 완료되었습니다. =");
						System.out.println("============================");
					}
				} else {
					System.out.println("신용도를 정확하게 입력해주세요.");
					return;
				}
			}		
		}
	}
	public void deleteAccount() {
		HighCreditAccount high = new HighCreditAccount(accNum, accName, accBalance, accInterest, accGrade, accType, accGradeRest, accFinalRest);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제할 계좌번호 : "); accNum = scan.nextLine();		
		boolean dCheck = bankAccount.add(high);
		
		if(dCheck == false) {
			System.out.println("= 입력하신 계좌정보와 동일한 계좌를 찾았습니다. =");
			for(Account acc : bankAccount) {
				if(accNum.compareTo(acc.num)==0) {
					acc.showAccInfo();
					System.out.println("============================");
				}
			}
			
			int choice3 = 0;
			boolean deleteSelect = false;
			
			while(!deleteSelect) {
				System.out.println("= 원하시는 메뉴를 선택하세요. =");
				System.out.println("");
				System.out.println(" 1.선택한 계좌 삭제하기");
				System.out.println(" 2.삭제 취소 후 종료");
				System.out.println("");
				System.out.println("메뉴 선택 : ");
				
				Scanner scan2 = new Scanner(System.in);
				choice3 = scan2.nextInt();
				
				if(choice3 == 1) {
					bankAccount.remove(high);
					System.out.println("= 기존 계좌 삭제가 완료되었습니다. =");
					System.out.println("============================");
					return;
				} else if(choice3 == 2) {
					System.out.println("= 기존 계좌 삭제가 취소되었습니다. =");
					System.out.println("============================");	
					return;
				} else {
					System.out.println("정확한 메뉴를 선택하세요.");						
				}
			}			
		} else if(dCheck == true) {
			System.out.println("= 삭제할 계좌 정보를 확인해주세요. =");
			System.out.println("============================");
		}	
	}
	public void saveAccount() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking5/AccountInfo.obj"));
			for(Account acc : bankAccount) {
				out.writeObject(acc);
				System.out.println("AccountInfo.obj 파일 저장이 완료되었습니다.");
			} 
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("저장된 파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("IO에러가 발생했습니다.");
		}
	}
	public void readAccount() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("src/banking5/AccountInfo.obj"));
			while(true) {
				Account acc = (Account)in.readObject();
				bankAccount.add(acc);
			}
		} catch(FileNotFoundException e) {
			System.out.println("저장된 파일이 없습니다.");
		} catch(EOFException e) {
			System.out.println("파일의 복원이 완료되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외가 발생하였습니다.");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void depositMoney() {
			
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요 : ");
		String searchName = scan.nextLine();
		
		try {
			for(Account acc : bankAccount) {
				
				balanceInt = (int) (acc.balance * (accInterest + accGradeRest) / 100);
				
				if(searchName.compareTo(acc.num)==0) {
						
					System.out.println("= 입력하신 계좌정보를 찾았습니다. =");
					
					acc.showAccInfo();
					System.out.println("============================");
	
					System.out.println(" 입금액 : "); deposit = scan.nextInt();
					
					if(deposit < 0) {
						System.out.println("입금액은 음수가 될 수 없습니다.");
					} else if (deposit % 500 > 0) {
						System.out.println("입금은 500원 단위로만 가능합니다.");
					} else {
						System.out.println(" 입금액 : " + deposit);
						System.out.println(" 기존 잔액 : " + acc.balance); 
						System.out.println(" 이자 : " + balanceInt); 
						System.out.println(" 최종 잔액 : "+(acc.balance + balanceInt + deposit)+"원");
						System.out.println("============================");
						
						acc.balance = acc.balance + balanceInt + deposit;
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
			for(Account acc : bankAccount) {
				if(searchName.compareTo(acc.num)==0) {
					System.out.println("= 입력하신 계좌정보를 찾았습니다. =");
	
					acc.showAccInfo();
					System.out.println("============================");
					
					System.out.println("출금액 : "); deposit = scan.nextInt();
					
					if (acc.balance - deposit < 0) {
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
								acc.balance = 0;		
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
						System.out.println(" 잔액 : "+(acc.balance - deposit)+"원");
						System.out.println("============================");
						acc.balance = acc.balance - deposit;		
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
		for(Account acc : bankAccount) {
			acc.showAccInfo();
		}
	}
}