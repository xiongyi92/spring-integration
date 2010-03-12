/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.loanbroker.demo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.loanbroker.LoanBrokerGateway;
import org.springframework.integration.loanbroker.domain.Customer;
import org.springframework.integration.loanbroker.domain.LoanQuote;
import org.springframework.integration.loanbroker.domain.LoanRequest;

/**
 * @author Oleg Zhurakousky
 *
 */
public class LoanBrokerDemo {
	private static Logger logger = Logger.getLogger(LoanBrokerDemo.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext ac = 
						new ClassPathXmlApplicationContext("bootstrap-config/stubed-loan-broker.xml");
		LoanBrokerGateway broker = ac.getBean("loanBrokerGateway", LoanBrokerGateway.class);
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setCustomer(new Customer());   
		
		LoanQuote loan = broker.getLoanQuote(loanRequest);
		logger.info("\n********* Best Quote: " + loan);
		
		List<LoanQuote> loanQuotes = broker.getAllLoanQuotes(loanRequest);
		logger.info("\n********* All Quotes: ");
		for (LoanQuote loanQuote : loanQuotes) {
			logger.info(loanQuote);
		}
		
		ac.close();
	}

}
