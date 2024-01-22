package banking2;

public class HighCreditAccount extends NormalAccount {
	
	int gradeRest, finalRest;
	
	public HighCreditAccount(String num, String name, int balance, int interest, String grade, String type, int gradeRest, int finalRest) {
		super(num, name, balance, interest, grade, type);
		this.gradeRest = gradeRest;
		this.finalRest = finalRest;		
	}

	@Override
	public void interestCal() {
		super.interest = interest;
		
		if(grade == "A") {
			gradeRest = 7;
		} else if(grade == "B") {
			gradeRest = 4;
		} else if(grade == "C") {
			gradeRest = 2;
		} else {
			gradeRest = 0;
		}

		finalRest = interest + gradeRest;		
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println(" 추가 이율 : " + gradeRest + "%");
		System.out.println(" 최종 이율 : " + finalRest + "%");
	}
}
