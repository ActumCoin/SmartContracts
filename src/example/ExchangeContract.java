/* This example is for an exchange of real currency or non-Actum cryptocurrency with Actum tokens
 * For an exchange on ActumCrypto, use an atomic exchange instead
 */

import util.JSONReader;

public class ExchangeContract extends SmartContract {
  private boolean check() {
    JSONObject json = JSONReader.readJsonFromUrl("https://yourdomain.com/");
    if (json.get("sent").equals("true") {
      BigDecimal otherAmount = new BigDecimal(Integer.parseInt(json.get("amount")));
      BigDecimal exchangeRate = new BigDecimal(Integer.parseInt(json.get("rate")));
      BigDecimal amount = otherAmount.multiply(exchangeRate);
      balance = new Balance(amount, json.get("token");
      rxAddress = json.get("rx");
      return true;
    } else if (json.get("cancel").equals("true") {
      cancel = true;
    }
    return false;
  }
}
