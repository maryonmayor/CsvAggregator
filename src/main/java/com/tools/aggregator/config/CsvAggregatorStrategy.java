package com.tools.aggregator.config;

import java.util.ArrayList;
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
		/*
		 * List<List<String>> summation = new ArrayList<List<String>>() ;
		 * summation.add(new ArrayList<String>());
		 * summation.get(0).add(String.valueOf(newBody.size() + oldBody.size()));
		 */
		
		
		if(oldBody.addAll(newBody)) {
			oldExchange.getIn().setBody(oldBody);
		}
		/*
		 * if(oldBody.addAll(summation)) { oldExchange.getIn().setBody(oldBody); }
		 */
		return oldExchange;
	}

}
