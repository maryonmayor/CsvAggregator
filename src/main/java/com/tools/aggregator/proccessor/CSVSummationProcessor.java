package com.tools.aggregator.proccessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CSVSummationProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		List<List<String>> oldExchange = (List<List<String>>) exchange.getIn().getBody();
		int summation = 0;
		for (int i = 1; i < oldExchange.size(); i++) {
			summation += Integer.parseInt(oldExchange.get(i).get(1));
		}
		List<String> appendor = new ArrayList<String>();
		appendor.add("");
		appendor.add(String.valueOf(summation));
		
		oldExchange.add(appendor);
		exchange.getIn().setBody(oldExchange);

	}

}
