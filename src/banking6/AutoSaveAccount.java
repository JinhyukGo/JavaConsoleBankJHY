package banking6;

public class AutoSaveAccount extends Thread {
	
	AccountManager am;
	
	public AutoSaveAccount(AccountManager am) {
		this.am = am;
	}
	@Override
	public void run() {
		try {
			while(true) {
				am.saveAccount();
				sleep(3000);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}