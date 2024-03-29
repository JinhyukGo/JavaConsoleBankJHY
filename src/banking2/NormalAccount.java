package banking2;

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
}
