package banking2;

public class NormalAccount extends Account {
	
	int interest;
	String grade;

	public NormalAccount(String num, String name, int balance) {
		super(num, name, balance);
		this.interest = interest;
		this.grade = grade;
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("이율 : " + interest);
		System.out.println("신용도 : " + grade);
	}
}
