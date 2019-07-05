package com.tools.aggregator.config;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class CsvAggregatorStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(oldExchange == null) {
			return newExchange;
		}
		
		List<List<String>> oldBody = (List<List<String>>) oldExchange.getIn().getBody();
		List<List<String>> newBody = (List<List<String>>) newExchange.getIn().getBody();
		newBody.remove(0);//remove headers of 2nd part
		
		if(oldBody.addAll(newBody))
			oldExchange.getIn().setBody(oldBody);
		
		return oldExchange;
	}

}
