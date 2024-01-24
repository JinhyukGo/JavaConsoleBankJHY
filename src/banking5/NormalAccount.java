package banking5;

import java.io.Serializable;
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
        return super.num.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
    	NormalAccount accountobj = (NormalAccount) obj;
    	if(accountobj.num.equals(super.num)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
