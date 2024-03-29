package banking5;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
	String num, name;
	int balance, interest;
	
	public Account(String num, String name, int balance) {
		this.num = num;
		this.name = name;
		this.balance = balance;
	}
	public void showAccInfo() {
		System.out.println("----------------------------");
		System.out.println(" 계좌번호 : " + num);
		System.out.println(" 이름 : " + name);
		System.out.println(" 잔액 : " + balance);
	}
}