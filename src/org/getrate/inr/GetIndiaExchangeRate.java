package org.getrate.inr;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetIndiaExchangeRate {

	RateList rateList = new RateList();

	public static void main(String[] args) {
		GetIndiaExchangeRate indiaExchange = new GetIndiaExchangeRate();
		RateList rateList1 = indiaExchange.retriveExchangeRates();

		Collections.sort(rateList1);
		for (Iterator iterator = rateList1.iterator(); iterator.hasNext();) {
			RateModel rateModel = (RateModel) iterator.next();
			System.out.println("Company = " + rateModel.getCompany() + ", Rate = " + rateModel.getRate());
		}

		for (Iterator<RateModel> rateIterator = rateList1.iterator(); rateIterator.hasNext();) {
			System.out.println(rateIterator.next().getRate() + " | " + rateIterator.next().getCompany());
		}

	}

	public RateList retriveExchangeRates() {

		String htmlDocument = createTransferFile();

		Document doc;

		doc = Jsoup.parse(htmlDocument);

		Elements table = doc.select(".txt15"); // a with href
		Elements trs = table.get(0).getElementsByTag("tr");
		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.getElementsByTag("td");
			if (tds.size() >= 2) {
				try {
					rateList.addRateModel(tds.get(0).text(), tds.get(1).getElementsByTag("div").get(1).getAllElements()
							.get(0).text().substring(2).substring(0, 5), null);
					// System.out.println(tds.get(0).text()
					// + "|"
					// + tds.get(1).getElementsByTag("div").get(1)
					// .getAllElements().get(0).text()
					// .substring(2).substring(0,5));
				} catch (Exception e) {
					// System.out.println(e.getMessage());
				}
			} else {
				// System.out.println(tds.get(0).text() + "|");
			}
		}

		return rateList;
	}

	private String createTransferFile() {

		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		StringBuffer htmlDocument = new StringBuffer();
		try {
			url = new URL("http://www.compareremit.com/todays-best-dollar-to-rupee-exchange-rate/");
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				htmlDocument.append(line);
			}

		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		return htmlDocument.toString();
	}

}
