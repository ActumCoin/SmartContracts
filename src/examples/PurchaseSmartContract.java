package examples;

import java.math.BigDecimal;

import balance.Balance;
import balance.BalanceManager;
import multichain.command.MultichainException;

public class Purchase {
	private boolean cancel;

	private boolean check(Balance balance, String address) throws MultichainException {
		return BalanceManager.getInstance().checkReceived(balance, address);
	}
	
	public boolean execute(Balance balance, String address) throws MultichainException {
		while (!check(balance, address)) {
			System.out.println("a");
			if (cancel) {
				return false;
			}
		}
		// returns true if purchase was sent, now send the product!!
		return true;
	}

}
