package banking7;

public class AutoSaver extends Thread {
	
	AccountManager am;
	
	public AutoSaver() {
		am = new AccountManager();
		this.am = am;
	}
	@Override
	public void run() {
		try {
			while(true) {
				am.autoSave();
				sleep(3000);
			}
		} catch(NullPointerException e) {
			System.out.println(" ");
		} catch(InterruptedException e) {
			System.out.println(" ");
		}
	}
}
