public class SmartContract {
  private boolean cancel;
  
  private boolean check() {
    // where the smart contract is executed prior to payment
    return true;
  }
  
  public boolean execute(Balance balance, String txAddress, String rxAddress) {
    while(!check()) {
      if (cancel) {
        return false;
      }
    }
    SendManager.getInstance().send(balance, txAddress, rxAddress);
    return true;
  }
  
}
