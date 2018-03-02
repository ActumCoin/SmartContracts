package balance;

import java.math.BigDecimal;

public class Balance {

	private String token;
	private BigDecimal value;
	
	public Balance(String t, BigDecimal v) {
		token = t.toUpperCase();
		value = v;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getToken() {
		return token;
	}
	
	public String toString() {
		return token + " " + value.toString();
	}
	
}
