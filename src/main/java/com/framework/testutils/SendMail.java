package com.framework.testutils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail

{

	public Map<String, Set<String>> executionDetails = null;
	public static String USER_DIR = "user.dir";

	public void setExecutionDetails(Map<String, Set<String>> executionDetails) {
		this.executionDetails = executionDetails;
	}

	public static void main(String[] args) {

		SendMail sd = new SendMail();
		String tos = "prabhudatta.c@tadigital.com";//
		String ccs = "radhika.i@tadigital.com";//
		String bccs = "";//
		String userName = "prabhudatta.c@tadigital.com";
		String passWord = "";
		String host = "smtp.office365.com";
		String port = "587";
		String starttls = "true";
		String auth = "true";
		String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
		String fallback = "false";

		HashMap<String, List<String>> filetobeSent = new HashMap<>();
		List<String> files3 = new CopyOnWriteArrayList<>();
		System.out.println("files to be sent after execcution >> " + filetobeSent);
		Map<String, Set<String>> detailsTest2 = sd.getExtentReports();
		for (String suite : detailsTest2.keySet()) {
			for (String test : detailsTest2.get(suite)) {
				files3.add(test);
				filetobeSent.put(suite, files3);
			}
		}

		System.out.println("Final files to be sent after execcution >> " + filetobeSent);
		String attachmentPath = System.getProperty("user.dir");
		String to[] = { tos };
		String cc[] = { ccs, "koti@tadigital.com", "Umamalleswari.v@tadigital.com" };// ""
		String bcc[] = { bccs };

		SendMail.sendMail(userName, passWord, host, port, starttls, auth, false, socketFactoryClass, fallback, to, cc,
				bcc, "Automation execution Result: " + new Date(),
				"Please check the excel report for more details on execution. Or you can log into 172.24.31.207 machine and find the detail result @C:/AutomationResult ",
				attachmentPath, filetobeSent);
	}

	public static boolean sendMail(String userName, String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass, String fallback, String[] to, String[] cc,
			String[] bcc, String subject, String text, String attachmentPath,
			HashMap<String, List<String>> filetobeSent) {

		// Object Instantiation of a properties file.
		Properties props = new Properties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);

		if (!"".equals(port)) {
			props.put("mail.smtp.port", port);
		}

		if (!"".equals(starttls)) {
			props.put("mail.smtp.starttls.enable", starttls);
			props.put("mail.smtp.auth", auth);
		}

		if (debug) {

			props.put("mail.smtp.debug", "true");

		} else {

			props.put("mail.smtp.debug", "false");

		}

		if (!"".equals(port)) {
			props.put("mail.smtp.socketFactory.port", port);
		}
		if (!"".equals(socketFactoryClass)) {
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		}
		if (!"".equals(fallback)) {
			props.put("mail.smtp.socketFactory.fallback", fallback);
		}

		try {

			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setSubject(subject);

			Multipart multipart = new MimeMultipart();

			int j = 0;
			for (String location : filetobeSent.keySet()) {
				System.out.println("location >>> " + location);

				for (int i = 0; i < filetobeSent.get(location).size(); i++) {
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					File att = new File(new File(attachmentPath + location), filetobeSent.get(location).get(i));
					DataSource source = new FileDataSource(att.getPath());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(att.getName());
					multipart.addBodyPart(messageBodyPart, j);
					j++;
				}
			}

			msg.setContent(multipart);
			msg.setFrom(new InternetAddress(userName));

			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}

			for (int i = 0; i < cc.length; i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			}

			for (int i = 0; i < bcc.length; i++) {
				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
			}

			MimeBodyPart messageBodyPart1 = new MimeBodyPart();

			messageBodyPart1.setText(text);

			multipart.addBodyPart(messageBodyPart1, 1);
			msg.saveChanges();

			Transport transport = session.getTransport("smtp");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;

		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}

	}

	public Map<String, Set<String>> getExtentReports() {
		this.executionDetails = new ConcurrentHashMap<>();

		String sureFireReportPath = System.getProperty("user.dir");
		File[] directories = new File(sureFireReportPath).listFiles();
		Set<String> testFiles = new HashSet<String>();

		for (File dir : directories) {
			if (dir.getName().contains("extentreport.html")) {
				testFiles.add(dir.getName());
				executionDetails.put(sureFireReportPath, testFiles);
			}

		}
		return executionDetails;
	}
}