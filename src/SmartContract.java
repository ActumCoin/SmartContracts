public class SmartContract {
  private Balance balance;
  private String txAddress;
  private String rxAddress;
  
  public SmartContract(Balance b, String ta, String ra) {
    balance = b;
    txAddress = ta;
    rxAddress = ra;
  }
  
  private boolean check() {
    // where the smart contract is executed prior to payment
    return true;
  }
  
  public void execute() {
    while(!check());
    SendManager.getInstance().send(balance, txAddress, rxAddress);
  }
  
}
