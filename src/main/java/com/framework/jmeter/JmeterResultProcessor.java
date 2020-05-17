/**
 * 
 */
package com.framework.jmeter;

import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author prabhudatta.choudhur
 *
 */
@SuppressWarnings("serial")
public class JmeterResultProcessor extends ResultCollector {

	public JmeterResultProcessor(Summariser summer) {
		super(summer);
	}

	@Override
	public void sampleOccurred(SampleEvent se) {
		super.sampleOccurred(se);
		SampleResult r = se.getResult();
		if (r.isSuccessful()) {
			System.out.println("Response time in milliseconds: " + r.getTime());
		}
	}
}
