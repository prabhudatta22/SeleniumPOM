/**
 * 
 */
package com.techaspect.framework.jmeter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;

import org.apache.jmeter.control.LoopController;
/**
 * @author prabhudatta.choudhur
 *
 */
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.gui.tree.JMeterTreeListener;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.testng.annotations.Test;

import com.framework.jmeter.JmeterResultProcessor;
import com.framework.setup.TestSetUp;
import com.framework.testutils.TestUtils;

public class JmeterRunner extends TestSetUp {

	static String JMX_FILE_LOCATION = System.getProperty("user.dir") + "/src/main/jmeter/";

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testTIBAPerformance(Hashtable<String, String> data) throws Exception {
		// JMeter Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		// Initialize Properties, logging, locale, etc.
		JMeterUtils.loadJMeterProperties(TestSetUp.configProperty.getProperty("jmeter_prop"));
		JMeterUtils.setJMeterHome(TestSetUp.configProperty.getProperty("jmeter_home"));
		JMeterUtils.initLocale();

		// Initialize JMeter SaveService
		SaveService.loadProperties();

		// Load existing .jmx Test Plan
		File jmxFile = new java.io.File(JMX_FILE_LOCATION + "Internalapplogin50.jmx");
		HashTree testPlanTree = SaveService.loadTree(jmxFile);

		// Run JMeter Test
		// jmeter.configure(testPlanTree);
		// jmeter.run();

		TestPlan testPlan = new TestPlan(JMX_FILE_LOCATION + "Internalapplogin50.jmx");
		HashTree hashTree = new HashTree();

		HTTPSampler httpSampler = new HTTPSampler();
		httpSampler.setDomain(TestSetUp.configProperty.getProperty("url"));

		// Loop Controller
		TestElement loopCtrl = new LoopController();
		((LoopController) loopCtrl).setLoops(1);
		((LoopController) loopCtrl).addTestElement(httpSampler);
		((LoopController) loopCtrl).setFirst(true);

		// Thread Group
		SetupThreadGroup threadGroup = new SetupThreadGroup();
		threadGroup.setNumThreads(10);
		threadGroup.setRampUp(2);
		threadGroup.setSamplerController((LoopController) loopCtrl);
		threadGroup.getOnErrorStartNextLoop();

		testPlan.addThreadGroup(threadGroup);

		hashTree.add("testPlan", testPlan);
		hashTree.add("loopCtrl", loopCtrl);
		hashTree.add("threadGroup", threadGroup);
		hashTree.add("httpSampler", httpSampler);
		hashTree.add(testPlan, threadGroup);

		JMeterTreeListener jmeterOutListener = new JMeterTreeListener();
		hashTree.add(hashTree.getArray()[0], jmeterOutListener);

		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
			System.out.println(summer.getThreadName());
		}

		ResultCollector rsc = new ResultCollector(summer);
		jmeter.register(new JmeterResultProcessor(summer));
		jmeter.register(rsc);

		String logFile = JMX_FILE_LOCATION + "/PerformanceResult/PerformanceResult_" + ".jtl";
		ResultCollector logger = new ResultCollector(summer);
		logger.setFilename(logFile);
		logger.setEnabled(true);
		logger.setErrorLogging(true);

		Object res[] = hashTree.getArray();
		for (Object ores : res) {
			hashTree.add(ores, logger);
		}

		jmeter.configure(hashTree);
		jmeter.run();

		SaveService.saveTree(hashTree,
				new FileOutputStream(new File(JMX_FILE_LOCATION + "/PerformanceResult/PerformanceResult_" + ".xml")));
		System.out.println(hashTree);
		jmeter.exit();
	}
}