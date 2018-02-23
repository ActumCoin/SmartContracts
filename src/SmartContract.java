public class SmartContract {
  private boolean cancel;
  private Balance balance = null;
  private String txAddress = null;
  private String rxAddress = null;
  
  private boolean check() {
    // where the smart contract is executed prior to payment
    return true;
  }
  
  public boolean execute(Balance b, String t, String r) {
    balance = balance != null ? balance : b;
    txAddress = txAddress != null ? txAddress : t;
    rxAddress = rxAddress != null ? rxAddress : r;
    while(!check()) {
      if (cancel) {
        return false;
      }
    }
    SendManager.getInstance().send(balance, txAddress, rxAddress);
    return true;
  }
  
}
