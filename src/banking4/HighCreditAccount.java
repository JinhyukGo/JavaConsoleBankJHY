package banking4;

import java.util.Objects;

public class HighCreditAccount extends NormalAccount {
	
	int gradeRest, finalRest;
	
	public HighCreditAccount(String num, String name, int balance, int interest, String grade, String type, int gradeRest, int finalRest) {
		super(num, name, balance, interest, grade, type);
		this.gradeRest = gradeRest;
		this.finalRest = finalRest;		
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println(" 추가 이율 : " + gradeRest + "%");
		System.out.println(" 최종 이율 : " + finalRest + "%");
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
