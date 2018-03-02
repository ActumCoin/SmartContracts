package mining;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import multichain.command.MultichainException;

public class ForwardSmartContract {
	private static boolean cancel;
	private static String txAddress;
	private static String rxAddress;
	private static ForwardSmartContract instance = null;
	public Thread thread = new Thread() {
		public void run() {
			try {
				ForwardSmartContract.execute(ForwardSmartContract.getInstance());
			} catch (MultichainException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public ForwardSmartContract(String t, String r) {
		txAddress = t;
		rxAddress = r;
	}

	private static boolean check(String address) throws MultichainException {
		return BalanceManager.getInstance().checkReceivedGreater(new Balance("", BigDecimal.ZERO), address);

	}
	
	public static boolean execute(ForwardSmartContract fsc) throws MultichainException, InterruptedException {
		while (!check(fsc.getTxAddress())) {
			TimeUnit.MINUTES.sleep(2);
			if (cancel) {
				return false;
			}
		}
		BalanceManager.getInstance().send(BalanceManager.getInstance().lastReceived(fsc.getTxAddress()), fsc.getTxAddress(), fsc.getRxAddress());
		return true;
	}

	public static ForwardSmartContract getInstance() {
		return instance;
	}

	public static void setInstance(String t, String r) {
		instance = new ForwardSmartContract(t, r);
	}

	public String getTxAddress() {
		return txAddress;
	}

	public static String getRxAddress() {
		return rxAddress;
	}

}