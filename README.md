<img src="https://actumcrypto.org/svg/logo.svg">

# SmartContracts
Smart Contracts System for ActumCrypto

## Usage
### To create a smart contract:
```java
SmartContract sc = new SmartContract() {
                      private boolean check() {
                         // whatever should be done to verify if smart contract should be executed
                         return true;
                      }
                   };
```
`check()` should return `true` if the contract should be executed, `false` if it should wait, or set `stop = true` and return `false` to cancel.

### To execute a smart contract:
```java
sc.execute(balance, txAddress, rxAddress);
```
Pass the `Balance` object containing the amount of and token the contract should send upon execution, and the address sending (`txAddress`) and recieving (`rxAddress`). `execute()` will return `true` if it is succesful and `false` if it is cancelled.

### To create a reusable smart contract
```java
public class MyNewSmartContract extends SmartContract {
  private boolean check() {
     // check your way
     return true;
  }
}

MyNewSmartContract mnsc = new MyNewSmartContract();
mnsc.execute(balanceA, txAddressA, rxAddressA);
mnsc.execute(balanceB, txAddressB, rxAddressB);
```
