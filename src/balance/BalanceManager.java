package balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;
import multichain.object.BalanceAssetBase;
import multichain.object.TransactionWallet;

public class BalanceManager {

	private MultiChainCommand mCommand;
	private String address;
	private static BalanceManager instance = null;

	public BalanceManager(MultiChainCommand m, String a) {
		mCommand = m;
		address = a;
	}

	public BalanceManager(MultiChainCommand m) {
		mCommand = m;
	}

	public void send(Balance balance, String rxAddress) throws MultichainException {
		List<BalanceAssetBase> babList = new ArrayList<BalanceAssetBase>();
		BalanceAssetBase bab = new BalanceAssetBase();
		bab.setName(balance.getToken());
		bab.setQty(balance.getValue().doubleValue());
		System.out.println(bab);
		babList.add(bab);
		mCommand.getWalletTransactionCommand().sendFromAddress(address, rxAddress, babList);
	}

	public void send(Balance balance, String txAddress, String rxAddress) throws MultichainException {
		List<BalanceAssetBase> babList = new ArrayList<BalanceAssetBase>();
		BalanceAssetBase bab = new BalanceAssetBase();
		bab.setName(balance.getToken());
		bab.setQty(balance.getValue().doubleValue());
		System.out.println(bab);
		babList.add(bab);
		mCommand.getWalletTransactionCommand().sendFromAddress(txAddress, rxAddress, babList);
	}

	/**
	 * checkReceived
	 * 
	 * @param balance
	 *            the balance to check
	 * @param rxAddress
	 *            the address to check
	 * @returns true if balance equal to balance was last received at address
	 *          rxAddress, else false
	 */
	public boolean checkReceived(Balance balance, String rxAddress) throws MultichainException {
		TransactionWallet tw = mCommand.getWalletTransactionCommand().listAddressTransactions(rxAddress, 1).get(0);
		try {
			return balance.getValue().equals(new BigDecimal(tw.getBalance().getAssets().get(0).getQty()));
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * checkReceivedGreater
	 * 
	 * @param balance
	 *            the balance to check
	 * @param rxAddress
	 *            the address to check
	 * @returns true if balance greater than balance was last received at address
	 *          rxAddress, else false
	 */
	public boolean checkReceivedGreater(Balance balance, String rxAddress) throws MultichainException {
		TransactionWallet tw = mCommand.getWalletTransactionCommand().listAddressTransactions(rxAddress, 1).get(0);
		try {
			return balance.getValue().compareTo(new BigDecimal(tw.getBalance().getAssets().get(0).getQty())) == 1;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * checkReceivedGreaterEqual
	 * 
	 * @param balance
	 *            the balance to check
	 * @param rxAddress
	 *            the address to check
	 * @returns true if balance greater than or equal to balance was last received
	 *          at address rxAddress, else false
	 */
	public boolean checkReceivedGreaterEqual(Balance balance, String rxAddress) throws MultichainException {
		TransactionWallet tw = mCommand.getWalletTransactionCommand().listAddressTransactions(rxAddress, 1).get(0);
		try {
			return balance.getValue().compareTo(new BigDecimal(tw.getBalance().getAssets().get(0).getQty())) >= 0;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * checkReceivedLesser
	 * 
	 * @param balance
	 *            the balance to check
	 * @param rxAddress
	 *            the address to check
	 * @returns true if balance lesser than balance was last received at address
	 *          rxAddress, else false
	 */
	public boolean checkReceivedLesser(Balance balance, String rxAddress) throws MultichainException {
		TransactionWallet tw = mCommand.getWalletTransactionCommand().listAddressTransactions(rxAddress, 1).get(0);
		try {
			return balance.getValue().compareTo(new BigDecimal(tw.getBalance().getAssets().get(0).getQty())) == -1;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * checkReceivedLesserEqual
	 * 
	 * @param balance
	 *            the balance to check
	 * @param rxAddress
	 *            the address to check
	 * @returns true if balance lesser than or equal to balance was last received at
	 *          address rxAddress, else false
	 */
	public boolean checkReceivedLesserEqual(Balance balance, String rxAddress) throws MultichainException {
		TransactionWallet tw = mCommand.getWalletTransactionCommand().listAddressTransactions(rxAddress, 1).get(0);
		try {
			return balance.getValue().compareTo(new BigDecimal(tw.getBalance().getAssets().get(0).getQty())) <= 0;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * lastRecieved
	 * 
	 * @param rxAddress
	 *            the address to check
	 * @returns balance of last asset transaction received at address (up to 10),
	 *          null if none
	 */
	public Balance lastReceived(String address) throws MultichainException {
		TransactionWallet tw = null;
		for (int i = 0; i < 10; i++) {
			tw = mCommand.getWalletTransactionCommand().listAddressTransactions(address, 1, i).get(0);
			if (tw.getBalance().getAssets().get(0).getQty() > 0) {
				i = 10;
			}
		}
		if (tw == null) {
			return null;
		}
		return new Balance(tw.getBalance().getAssets().get(0).getName(),
				new BigDecimal(tw.getBalance().getAssets().get(0).getQty()));
	}

	public static BalanceManager getInstance() {
		return instance;
	}

	public static void createInstance(MultiChainCommand m, String a) {
		instance = new BalanceManager(m, a);
	}

	public static void createInstance(MultiChainCommand m) {
		instance = new BalanceManager(m);
	}

	public MultiChainCommand getmCommand() {
		return mCommand;
	}

}
