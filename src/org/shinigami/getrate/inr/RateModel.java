package org.shinigami.getrate.inr;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * @projectName GetExchangeRate
 * Creation date: May 31, 2015
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */

public class RateModel implements Comparable {
	private String key;
	private String company;
	private String rate;
	private float rateDecimal;
	private String date;

	public RateModel() {

	}

	public RateModel(String company, String rate, String date) {
		this.company = company;
		this.rate = rate;
		this.date = date;
	}

	public float getRateDecimal() {
		return rateDecimal;
	}

	public void setRateDecimal(float rateDecimal) {
		this.rateDecimal = rateDecimal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
		rateDecimal = Float.parseFloat(rate);
	}

	@Override
	public int compareTo(Object o) {
		RateModel rateModel = (RateModel) o;
		return (this.rateDecimal > rateModel.rateDecimal) ? -1 : (this.rateDecimal < rateModel.rateDecimal) ? 1 : 0;
	}
}
