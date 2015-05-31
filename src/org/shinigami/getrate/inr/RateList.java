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

import java.util.ArrayList;
import java.util.Collections;

public class RateList extends ArrayList<RateModel> {
	public void addRateModel(String company, String rate, String date) {
		RateModel rateModel = new RateModel();
		rateModel.setCompany(company);
		rateModel.setRate(rate);
		rateModel.setDate(date);
		rateModel.setKey(prepareKey(rateModel.getCompany()));
		this.add(rateModel);

		Collections.sort(this);
	}

	private String prepareKey(String company) {
		return company.replaceAll("[^a-zA-Z]+", " ").trim();
	}
}
