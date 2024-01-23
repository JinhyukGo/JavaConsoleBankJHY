package banking4;

import java.util.Objects;

public class Account {
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
   @Override
    public int hashCode() {
        return Objects.hash(num);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        if (obj == null || getClass() != obj.getClass())
        	return false;
        Account account = (Account) obj;
        return num.equals(account.num);
    }
}