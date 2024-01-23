package banking4;

import java.util.Objects;

public class NormalAccount extends Account {
	
	int interest;
	String grade, type;

	public NormalAccount(String num, String name, int balance, int interest, String grade, String type) {
		super(num, name, balance);
		this.interest = interest;
		this.grade = grade;
		this.type = type;
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println(" 이율 : " + interest + "%");
		System.out.println(" 신용도 : " + grade);
		System.out.println(" 계좌타입 : "+ type);
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
