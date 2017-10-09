package com.crosspay.payment.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.Context;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.crosspay.payment.model.CustomTemplate;
import com.crosspay.payment.model.Receiptpdf;
import com.crosspay.payment.model.SPartnerResponse;
import com.crosspay.payment.model.SmtpAuthenticator;
import com.crosspay.payment.service.EmailService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
/*import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.xml.simpleparser.*;*/
import com.mysql.fabric.Response;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

@Service
@Async
public class EmailServiceImpl implements EmailService {

	Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	String path = null;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Async
	public void sendSimpleEmail(String fromAddr, String toAddr, String subject, String message) {

		System.out.println("Mail  " + fromAddr + "== " + toAddr + "==" + subject);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddr);
		msg.setTo(toAddr);
		msg.setSubject(subject);
		msg.setText(message);
		try {
			this.mailSender.send(msg);
			System.out.println("Mail sent successfully");
			logger.info("Mail sent successfully");
		} catch (MailException ex) {
			System.out.println("Error while snding EMail" + ex.getMessage());
			logger.error("Error while snding EMail" + ex.getMessage());
		}
	}

	@Override
	@Async
	public void sendMimeTypeEmail(String formAddr, String toAddress, String subject, String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	@Async
	public void sendMailWithTemplate(String templateName, Context vars, String toAddr, String subject) {

		// Prepare message using a Spring helper
		/*
		 * final MimeMessage mimeMessage = this.mailSender.createMimeMessage(); final
		 * MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
		 * "UTF-8"); // true = multipart message.setSubject(subject);
		 * message.setFrom("support@crosspay.com"); message.setTo(toAddr);
		 * 
		 * 
		 * final String htmlContent = ""; message.setText(htmlContent, true); // true =
		 * isHtml
		 * 
		 * // Send mail this.mailSender.send(mimeMessage);
		 */
	}

	@Override
	public void sendEmailTemplate(CustomTemplate custom) {
		System.out.println("notificationMsg asdasd");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);

		properties.put("mail.username", "support@crosspaymt.com");
		properties.put("mail.password", "Support789");
		Session session = Session.getDefaultInstance(properties, null);

		Message message = new MimeMessage(session);

		VelocityEngine velocityEngine = new VelocityEngine();
		Properties propss = new Properties();

		propss.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
		propss.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
		propss.setProperty("runtime.log.logsystem.log4j.category", "velocity");
		propss.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		propss.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		System.out.println("propss not " + propss);

		velocityEngine.init(propss);

		Map model = new HashMap();
		model.put("customTemplate", custom);
		String notificationMsg = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, custom.getTemplatename(),
				model);

		// Set From: header field of the header.
		try {
			message.setFrom(new InternetAddress("support@crosspaymt.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(custom.getEmail()));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("payments@crosspayments.com"));
			
			message.setSubject(custom.getSubject());
			message.setContent(notificationMsg, "text/html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("notificationMsg exc " + e.toString());

		}

		// Set To: header field of the header.

		try {
			Transport.send(message,"support@crosspaymt.com","Support789");
			
			logger.info("Mail sent successfully");
		} catch (MailException | MessagingException ex) {
			System.out.println("Error while snding EMail" + ex.getMessage());
			logger.error("Error while snding EMail" + ex.getMessage());
		}
	}

	@Override
	public void sendmailpdf(String fromAddr, String toAddr, String subject, String message) {
		// toAddr = "manishrai992@gmail.com";
		toAddr = "reply2sravanthi@gmail.com";
		try {
			path = new File("src/main/webapp/cp_services/pages/").getAbsolutePath();
			System.out.println("path val inside " + path);
		} catch (Exception e) {
			System.out.println("path val err " + e.toString());
			e.printStackTrace();
		}

		System.out.println("path val  " + path);
		ByteArrayOutputStream outputStream = null;

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(properties, null);

		try {
			// construct the text body part

			/*
			 * VelocityEngine velocityEngine = new VelocityEngine(); Properties propss = new
			 * Properties();
			 * 
			 * propss.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
			 * propss.setProperty("runtime.log.logsystem.class",
			 * "org.apache.velocity.runtime.log.Log4JLogChute");
			 * propss.setProperty("runtime.log.logsystem.log4j.category", "velocity");
			 * propss.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
			 * propss.setProperty("class.resource.loader.class",
			 * "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			 * 
			 * SPartnerResponse customTemplate2 = new SPartnerResponse(); Map model = new
			 * HashMap(); model.put("customTemplate", customTemplate2);
			 * 
			 * String notificationMsg = VelocityEngineUtils
			 * .mergeTemplateIntoString(velocityEngine, "mailreceipt.html", model);
			 */

			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(message);

			// now write the PDF content to the output stream

			outputStream = new ByteArrayOutputStream();

			// writePdf(outputStream);
			createPdf(outputStream);
			byte[] bytes = outputStream.toByteArray();
			/// outputStream.flush();
			// outputStream.close();

			// construct the pdf body part
			// DataSource dataSource = new FileDataSource(bytes, "application/pdf");
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			/*
			 * ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			 * ByteArrayInputStream cis = new
			 * ByteArrayInputStream(cssSource.toString().getBytes());
			 */
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("receipt.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			// create the sender/recipient addresses
			InternetAddress iaSender = new InternetAddress(fromAddr);
			InternetAddress iaRecipient = new InternetAddress(toAddr);

			// construct the mime message
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setSender(iaSender);
			mimeMessage.setSubject(subject);
			mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
			mimeMessage.setContent(mimeMultipart);

			// send off the email
			try {
				// Transport.send(mimeMessage);
				this.mailSender.send(mimeMessage);
			} catch (Exception e) {
				System.out.println("exception while sending pdf mail " + e.toString());
			}

			System.out.println("sent from " + fromAddr + ", to " + toAddr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// clean off
			if (null != outputStream) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception ex) {
				}
			}
		}
	}

	/*
	 * public void writePdf(OutputStream outputStream) throws Exception { Document
	 * document = new Document(); PdfWriter.getInstance(document, outputStream);
	 * PdfWriter writer = PdfWriter.getInstance(document, outputStream);
	 * writer.setInitialLeading(12); document.open(); VelocityEngine velocityEngine
	 * = new VelocityEngine(); Properties propss = new Properties();
	 * 
	 * propss.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
	 * propss.setProperty("runtime.log.logsystem.class",
	 * "org.apache.velocity.runtime.log.Log4JLogChute");
	 * propss.setProperty("runtime.log.logsystem.log4j.category", "velocity");
	 * propss.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
	 * propss.setProperty("class.resource.loader.class",
	 * "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	 * System.out.println("propss not "+propss);
	 * 
	 * 
	 * 
	 * propss.setProperty("resource.loader","webapp");
	 * propss.setProperty("webapp.resource.loader.class",
	 * "org.apache.velocity.tools.view.WebappResourceLoader");
	 * propss.setProperty("webapp.resource.loader.path",
	 * "/cp_services/WEB-INF/pages/");
	 * 
	 * String path = new File(".").getAbsolutePath();
	 * System.out.println("path in vvvv "+path.split(".")); path =
	 * path+"src/main/webapp/cp_services/";
	 * System.out.println("path in aaaa "+path);
	 * //"/absolute/path/to/templates/dir/on/your/machine";
	 * propss.put("file.resource.loader.path", path);
	 * propss.setProperty("runtime.log.logsystem.class",
	 * "org.apache.velocity.runtime.log.NullLogSystem");
	 * 
	 * velocityEngine.init(propss); Properties props = new Properties();
	 * props.put("file.resource.loader.path",
	 * "/Users/Projects/Comparator/src/main/resources/"); ve.init(props);
	 * 
	 * Template t = ve.getTemplate("helloworld.vm");
	 * 
	 * SPartnerResponse customTemplate2 = new SPartnerResponse(); Map model = new
	 * HashMap(); model.put("customTemplate", customTemplate2);
	 * System.out.println("model not "+model); VelocityContext velocityContext = new
	 * VelocityContext(model); StringWriter writer = new StringWriter();
	 * velocityEngine.mergeTemplate("mailreceipt.html", velocityContext, writer);
	 * String html = writer.toString(); String notificationMsg = VelocityEngineUtils
	 * .mergeTemplateIntoString(velocityEngine, "sample123.html", model); HTMLWorker
	 * htmlparser = new HTMLWorker(document); StringReader sr = new
	 * StringReader(notificationMsg);
	 * 
	 * // htmlparser.parse(sr); List<Element> objects = HTMLWorker.parseToList( new
	 * StringReader(notificationMsg), null, null); for (Element element : objects) {
	 * document.add(element); }
	 * 
	 * document.close(); }
	 */

	public void createPdf(OutputStream outputStream) throws IOException, DocumentException {
		final String CSS = "td { font-size: 7pt; border-color: #000; border: 1px;border-top:0px;border-right: 0px;}"
				+ ".logo{margin-top: 40px;}"
				+ "	.sideheadding{font-family:Times New Roman;border: 1px solid #E4E1E1;background: #E4E1E1;padding: 5px;}"
				+ " .innermatter {border: 1px solid #E4E1E1;}" + " .para{font-size: 18px;padding-top: 5px;}"
				+ "	  .para1{border-bottom: 2px solid #5F5D5D;padding-bottom: 0px;padding-top:2px;}"
				+ "	 .tabinfo{ padding-top: 20px;}" + "	  .order{ color: #000000;}" + "	   .arrow { padding-top: 50px;}"
				+ ".col-md-12 {width: 100%;border:1px solid #000}" + ".col-md-4 {width: 33.33333333%;}"
				+ "b{font-weight: 900;}" + "table-bordered {border: 1px solid #000;}"
				+ "table {width: 100%;max-width: 100%; margin-bottom: 20px;background-color: transparent; border-spacing: 0;border-collapse: collapse;}"
				+ "body { font-family: Garamond;font-size: 7pt;line-height: 1; color: #000;background-color: #fff;border:2px solid green;border-left: 10px solid #900;"
				+ "    border-right: 10px solid #900;  }"
				+ "tbody { display: table-row-group;vertical-align: middle;border-color: inherit;}"
				+ "tr {display: table-row; vertical-align: inherit;border-color: inherit;}"
				+ "table-bordered>tbody>tr>td, table-bordered>tbody>tr>th, table-bordered>tfoot>tr>td, table-bordered>tfoot>tr>th, table-bordered>thead>tr>td, table-bordered>thead>tr>th {border:1px solid #000;border-right: none;}"
				+ "p{line-height: 1; }"
				+ "table-nobordered>tbody>tr>td, table-nobordered>tbody>tr>th, table-nobordered>tfoot>tr>td, table-nobordered>tfoot>tr>th, table-nobordered>thead>tr>td, table-nobordered>thead>tr>th {border: none;}";

		// + "table>tbody>tr>td, table>tbody>tr>th, table>tfoot>tr>td,
		// table>tfoot>tr>th, table>thead>tr>td, table>thead>tr>th {padding: 8px;
		// line-height: 1;vertical-align: top; border-top: none;}"

		VelocityEngine velocityEngine = new VelocityEngine();
		Properties propss = new Properties();

		/*
		 * propss.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
		 * propss.setProperty("runtime.log.logsystem.class",
		 * "org.apache.velocity.runtime.log.Log4JLogChute");
		 * propss.setProperty("runtime.log.logsystem.log4j.category", "velocity");
		 * propss.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		 * propss.setProperty("class.resource.loader.class",
		 * "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		 * System.out.println("propss not "+propss);
		 */

		propss.put("file.resource.loader.path", path);
		propss.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");

		velocityEngine.init(propss);
		SPartnerResponse customTemplate2 = new SPartnerResponse();
		customTemplate2.setTXNREF("4523 6523 4123 8752");
		customTemplate2.setACCOUNTTYPEDESC("Savings Account");
		customTemplate2.setTXNSTARTTIMESTAMP(new Date());
		customTemplate2.setBASEAMOUNT(1520.36);
		customTemplate2.setCURRECY("GBP");
		customTemplate2.setMERCHANTNAME("John");
		customTemplate2.setISSUER("hgjhgjg");
		Map model = new HashMap();
		model.put("customTemplate", customTemplate2);
		String notificationMsg = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "CPReceipt.html", model);
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		document.open();

		/*
		 * Rectangle rect= new Rectangle(595,840); rect.setBorder(Rectangle.BOX);
		 * rect.setBorderWidth(2); rect.enableBorderSide(1); rect.enableBorderSide(2);
		 * rect.enableBorderSide(4); rect.enableBorderSide(8);
		 * rect.setBorderColor(BaseColor.BLACK);
		 * 
		 * document.add(rect);
		 */

		/*
		 * Rectangle rect = document.getPageSize(); rect.setBorder(Rectangle.BOX); //
		 * left, right, top, bottom border rect.setBorderWidth(1); // a width of 5 user
		 * units rect.setBorderColor(BaseColor.MAGENTA); // a red border
		 * rect.setUseVariableBorders(false); document.add(rect);
		 */
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
		cssResolver.addCss(cssFile);

		// HTML
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

		// Pipelines
		PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

		String HTML = notificationMsg;
		// XML Worker
		XMLWorker worker = new XMLWorker(css, true);
		XMLParser p = new XMLParser(worker);
		InputStream is = new ByteArrayInputStream(notificationMsg.getBytes());
		p.parse(new ByteArrayInputStream(HTML.getBytes()));
		document.close();
	}

	public void createPdfdy(OutputStream outputStream, String templatename, Receiptpdf receipt)
			throws IOException, DocumentException {
		final String CSS = "td { font-size: 7pt; border-color: #000; border: 1px;border-top:0px;border-right: 0px;}"
				+ ".logo{margin-top: 40px;}"
				+ "	.sideheadding{font-family:Times New Roman;border: 1px solid #E4E1E1;background: #E4E1E1;padding: 5px;}"
				+ " .innermatter {border: 1px solid #E4E1E1;}" + " .para{font-size: 18px;padding-top: 5px;}"
				+ "	  .para1{border-bottom: 2px solid #5F5D5D;padding-bottom: 0px;padding-top:2px;}"
				+ "	 .tabinfo{ padding-top: 20px;}" + "	  .order{ color: #000000;}" + "	   .arrow { padding-top: 50px;}"
				+ ".col-md-12 {width: 100%;border:1px solid #000}" + ".col-md-4 {width: 33.33333333%;}"
				+ "b{font-weight: 900;}" + "table-bordered {border: 1px solid #000;}"
				+ "table {width: 100%;max-width: 100%; margin-bottom: 20px;background-color: transparent; border-spacing: 0;border-collapse: collapse;}"
				+ "body { font-family: Garamond;font-size: 7pt;line-height: 1; color: #000;background-color: #fff;border:2px solid green;border-left: 10px solid #900;"
				+ "    border-right: 10px solid #900;  }"
				+ "tbody { display: table-row-group;vertical-align: middle;border-color: inherit;}"
				+ "tr {display: table-row; vertical-align: inherit;border-color: inherit;}"
				+ "table-bordered>tbody>tr>td, table-bordered>tbody>tr>th, table-bordered>tfoot>tr>td, table-bordered>tfoot>tr>th, table-bordered>thead>tr>td, table-bordered>thead>tr>th {border:1px solid #000;border-right: none;}"
				+ "p{line-height: 1; }"
				+ "table-nobordered>tbody>tr>td, table-nobordered>tbody>tr>th, table-nobordered>tfoot>tr>td, table-nobordered>tfoot>tr>th, table-nobordered>thead>tr>td, table-nobordered>thead>tr>th {border: none;}";

		// + "table>tbody>tr>td, table>tbody>tr>th, table>tfoot>tr>td,
		// table>tfoot>tr>th, table>thead>tr>td, table>thead>tr>th {padding: 8px;
		// line-height: 1;vertical-align: top; border-top: none;}"

		VelocityEngine velocityEngine = new VelocityEngine();
		Properties propss = new Properties();

		propss.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
		propss.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
		propss.setProperty("runtime.log.logsystem.log4j.category", "velocity");
		propss.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		propss.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		System.out.println("propss not " + propss);

		/*
		 * propss.put("file.resource.loader.path", path);
		 * propss.setProperty("runtime.log.logsystem.class",
		 * "org.apache.velocity.runtime.log.NullLogSystem");
		 */

		/*
		 * propss.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file");
		 * propss.setProperty("class.resource.loader.class",
		 * "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		 */

		velocityEngine.init(propss);
		/*
		 * SPartnerResponse customTemplate2 = new SPartnerResponse();
		 * customTemplate2.setTXNREF("4523 6523 4123 8752");
		 * customTemplate2.setACCOUNTTYPEDESC("Savings Account");
		 * customTemplate2.setTXNSTARTTIMESTAMP(new Date());
		 * customTemplate2.setBASEAMOUNT(1520.36); customTemplate2.setCURRECY("GBP");
		 * customTemplate2.setMERCHANTNAME("John");
		 * customTemplate2.setISSUER("hgjhgjg");
		 */
		Map model = new HashMap();
		model.put("customTemplate", receipt);
		String notificationMsg = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatename, model);

		logger.info("mail string pdf" + notificationMsg);
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		document.open();

		/*
		 * Rectangle rect= new Rectangle(595,840); rect.setBorder(Rectangle.BOX);
		 * rect.setBorderWidth(2); rect.enableBorderSide(1); rect.enableBorderSide(2);
		 * rect.enableBorderSide(4); rect.enableBorderSide(8);
		 * rect.setBorderColor(BaseColor.BLACK);
		 * 
		 * document.add(rect);
		 */

		/*
		 * Rectangle rect = document.getPageSize(); rect.setBorder(Rectangle.BOX); //
		 * left, right, top, bottom border rect.setBorderWidth(1); // a width of 5 user
		 * units rect.setBorderColor(BaseColor.MAGENTA); // a red border
		 * rect.setUseVariableBorders(false); document.add(rect);
		 */
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
		cssResolver.addCss(cssFile);

		// HTML
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

		// Pipelines
		PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

		String HTML = notificationMsg;
		// XML Worker
		XMLWorker worker = new XMLWorker(css, true);
		XMLParser p = new XMLParser(worker);
		InputStream is = new ByteArrayInputStream(notificationMsg.getBytes());
		p.parse(new ByteArrayInputStream(HTML.getBytes()));
		document.close();
	}

	@Override
	public String sendmailpdfdy(Receiptpdf receipt, String template, String fromAddr) {

		String ret = "";
		try {
			path = new File("src/main/webapp/cp_services/pages/").getAbsolutePath();
			System.out.println("path val inside " + path);
			logger.info("path template " + path);
		} catch (Exception e) {
			System.out.println("path val err " + e.toString());
			e.printStackTrace();
		}

		System.out.println("path val  " + path);
		ByteArrayOutputStream outputStream = null;

		Properties properties = new Properties();
		/*
		 * properties.put("mail.smtp.host", "smtp.gmail.com");
		 * properties.put("mail.smtp.port", "587");
		 */
		String host = "smtp.gmail.com";
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", "support@crosspaymt.com");
		properties.put("mail.smtp.password", "Support789");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		/*
		 * properties.put("mail.smtp.ssl.enable", "true");
		 * properties.put("mail.smtp.socketFactory.port", "587");
		 * properties.put("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 */

		// SmtpAuthenticator authentication = new SmtpAuthenticator();
		Session session = Session.getDefaultInstance(properties);
		/*
		 * Session session = Session.getInstance(properties, new
		 * javax.mail.Authenticator() { protected PasswordAuthentication
		 * getPasswordAuthentication() { return new
		 * PasswordAuthentication("support@crosspaymt.com", "Support789"); } });
		 */
		try {

			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("");

			// now write the PDF content to the output stream

			outputStream = new ByteArrayOutputStream();

			// writePdf(outputStream);
			createPdfdy(outputStream, template, receipt);
			byte[] bytes = outputStream.toByteArray();

			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("receipt.pdf");

			// construct the mime multi part
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			// create the sender/recipient addresses
			InternetAddress iaSender = new InternetAddress(fromAddr);
			InternetAddress iaRecipient = new InternetAddress(receipt.getCustomermail());//payments@crosspayments.comreceipt.getCustomermail()  payments@crosspayments.com
			InternetAddress bcRecipient = new InternetAddress("payments@crosspayments.com");//payments@crosspayments.com
			
			// construct the mime message
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setSender(iaSender);
			mimeMessage.setSubject("Crosspay Transaction Receipt");
			mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
			mimeMessage.setRecipient(Message.RecipientType.BCC, bcRecipient);
			mimeMessage.setContent(mimeMultipart);

			// send off the email
			try {
				// Transport.send(mimeMessage);
				Transport t = session.getTransport("smtp");
				t.connect("support@crosspaymt.com", "Support789");
				t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
				// this.mailSender.send(mimeMessage);
				ret = "mail sent success";
				logger.info("mail sent successfully");
			} catch (Exception e) {
				ret = "mail not sent " + e.toString();
				System.out.println("exception while sending pdf mail " + e.toString());
				logger.info("exception while sending pdf mail " + e.toString());
			}

			System.out.println("sent from " + fromAddr + ", to " + receipt.getCustomermail());

			logger.info("sent from " + fromAddr + ", to " + receipt.getCustomermail());
		} catch (Exception ex) {
			ret = "mail not" + ex.toString();
			ex.printStackTrace();
		} finally {
			// clean off
			if (null != outputStream) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception ex) {
				}
			}
		}

		return ret;
	}

}
