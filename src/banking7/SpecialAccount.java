package banking7;

public class SpecialAccount extends NormalAccount {

	public SpecialAccount(String num, String name, int balance, int interest, String grade, String type) {
		super(num, name, balance, interest, grade, type);
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
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
