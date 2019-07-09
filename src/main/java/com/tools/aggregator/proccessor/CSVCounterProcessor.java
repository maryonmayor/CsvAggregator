package com.tools.aggregator.proccessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CSVCounterProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		List<List<String>> oldExchange = (List<List<String>>) exchange.getIn().getBody();
		int total = 0;
		total += (oldExchange.size() - 1);
		List<String> appendor = new ArrayList<String>();
		appendor.add("");
		appendor.add(String.valueOf(total));
		
		oldExchange.add(appendor);
		exchange.getIn().setBody(oldExchange);

	}

}
