package com.cg.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SetMail extends TimerTask{
	InternetAddress[] Address;
	public SetMail(InternetAddress[] address) {
		super();
		Address = address;
	}
	@Override
	public void run() {
		emailSender(Address);
	}

	public InternetAddress[] setpromomail(InternetAddress[] toAddress, String startdate, String enddate, String duration, String starttime,String endtime) throws ParseException,InterruptedException{
		List<Object> st_list=new ArrayList<Object>();
		List<Object> et_list=new ArrayList<Object>();
		StringTokenizer st1=new StringTokenizer(startdate,"/");
		while(st1.hasMoreElements()){
			st_list.add(st1.nextElement());
		}
		int i1=Integer.valueOf((String) st_list.get(0));
		int i2=Integer.valueOf((String) st_list.get(1));
		int i3=Integer.valueOf((String) st_list.get(2));
		StringTokenizer st2=new StringTokenizer(starttime,":");
		while(st2.hasMoreElements()){
			st_list.add(st2.nextElement());
		}
		int i4=Integer.valueOf((String) st_list.get(3));
		int i5=Integer.valueOf((String) st_list.get(4));
		Calendar p_cal=Calendar.getInstance();
		Calendar s_cal=Calendar.getInstance();
		Calendar e_cal=Calendar.getInstance();
		s_cal.set(i3,i1-1,i2,i4,i5);
		long l1=s_cal.getTimeInMillis();
		long l2=p_cal.getTimeInMillis();
		StringTokenizer st3=new StringTokenizer(enddate,"/");
		while(st3.hasMoreElements()){
			et_list.add(st3.nextElement());
		}
		int i6=Integer.valueOf((String) et_list.get(0));
		int i7=Integer.valueOf((String) et_list.get(1));
		int i8=Integer.valueOf((String) et_list.get(2));
		StringTokenizer st4=new StringTokenizer(endtime,":");
		while(st4.hasMoreElements()){
			et_list.add(st4.nextElement());
		}
		int i9=Integer.valueOf((String) et_list.get(3));
		int i10=Integer.valueOf((String) et_list.get(4));
		e_cal.set(i8,i6-1,i7,i9,i10);
		long l3=e_cal.getTimeInMillis();
		long l4=l3-l1;
		long l5=l1-l2;
		int dur=Integer.valueOf(duration);
		for(int y=0;y<toAddress.length;y++){
			Address[y]=toAddress[y];
		}
		TimerTask timerTask = new SetMail(toAddress);
		Timer timer = new Timer(); 
		Thread.sleep(l5);
		timer.scheduleAtFixedRate(timerTask, 0,l4/dur);
		try {
			Thread.sleep(l4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
		return Address;
	}

	public boolean emailSender(InternetAddress[] toaddress) {
		String host="nsiore01.capgemini.com";
		final String from="capstore@capgemini.com";
		final String password="Ravi1991";
		Properties propertyobj=new Properties();
		propertyobj.put("mail.smtp.starttls.enable", "true");
		propertyobj.put("mail.smtp.host", host);
		propertyobj.put("mail.smtp.user", from);
		propertyobj.put("mail.smtp.password",password);
		propertyobj.put("mail.smtp.port", "25");
		propertyobj.put("mail.smtp.auth", "true");
		propertyobj.put("mail.debug", "true");
		Session session=Session.getDefaultInstance(propertyobj,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		try	{
			MimeMessage mimemessageobj = new MimeMessage(session);
			mimemessageobj.setFrom(new InternetAddress(from));
			mimemessageobj.addRecipients(Message.RecipientType.TO, toaddress);
			mimemessageobj.setSubject("SPECIAL OFFERS AND NEW ENTRIES IN CAPSTORE");

			MimeMultipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlcontent = "<H1 >SPECIAL OFFERS ARE WAITING FOR YOU!! AVAIL THEM TODAY</H1><br/><a href='http://www.google.com/'><p align=center><img src=\"cid:image\"> </p></a>";
			messageBodyPart.setContent(htmlcontent, "text/html");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource datasourceobj = new FileDataSource("C:\\EmailImages\\Promo.jpg");
			messageBodyPart.setDataHandler(new DataHandler(datasourceobj));
			messageBodyPart.addHeader("Content-ID","<image>");
			multipart.addBodyPart(messageBodyPart);

			mimemessageobj.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from,password);
			mimemessageobj.saveChanges();
			Transport.send(mimemessageobj);
			transport.close();
		}	
		catch(Exception exceptionobj)	{
			System.out.println(exceptionobj);
			return false;
		}
		return true;
	}

}

