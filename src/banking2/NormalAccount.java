package banking2;

public class NormalAccount extends Account {
	
	int interest;

	public NormalAccount(String num, String name, int balance) {
		super(num, name, balance);
		this.interest = interest;
		
		System.out.println("이율 : " + interest);
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("이율 : " + interest);
	}
}
