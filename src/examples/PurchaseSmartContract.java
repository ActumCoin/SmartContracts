package examples;

import java.math.BigDecimal;

import balance.Balance;
import balance.BalanceManager;
import multichain.command.MultichainException;

public class Purchase {
	private boolean cancel;

	private boolean check() throws MultichainException {
		return BalanceManager.getInstance().checkReceived(new Balance("acm", BigDecimal.ZERO), address);
	}
	
	public boolean execute() throws MultichainException {
		while (!check()) {
			System.out.println("a");
			if (cancel) {
				return false;
			}
		}
		// returns true if purchase was sent, now send the product!!
		return true;
	}

}
