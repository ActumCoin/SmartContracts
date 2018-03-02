<img src="https://actumcrypto.org/svg/logo.svg">

# SmartContracts
Smart Contracts System for ActumCrypto

## Usage
### To use a smart contract:
```java
PurchaseSmartContract psc = new PurchaseSmartContract();
if (psc.execute(balanceOfTheThingTheyBought, addressTheyShouldHavePaidTo)) {
  giveThemWhatTheyPaidFor();
}

ForwardSmartContract.setInstance(txAddress, rxAddress);
ForwardSmartContract.execute();
```
