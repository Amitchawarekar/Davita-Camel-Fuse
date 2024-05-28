package com.citiustech.restprojects.processors;

import java.util.Base64;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
	

public class BasicAuthProcessor implements Processor {
	
//	public String usrname;
//	public String passwd;
//	
//	
//	public String getUsrname() {
//		return usrname;
//	}
//	public void setUsrname(String usrname) {
//		this.usrname = usrname;
//	}
//	public String getPasswd() {
//		return passwd;
//	}
//	public void setPasswd(String passwd) {
//		this.passwd = passwd;
//	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
//		System.out.println(getPasswd() + getUsrname());
		String AuthHeader = exchange.getIn().getHeader("Authorization", String.class);
		if (exchange.getIn().getHeader("Authorization", String.class) == null) {
			exchange.getIn().setHeader("SuccessfulAuthorization", false);
		} else {
			byte[] decodedBytes = Base64.getDecoder().decode(AuthHeader.replace("Basic ", ""));
			String[] decodedString = new String(decodedBytes).split(":");
			if (decodedString[0].equals("admin") && decodedString[1].equals("admin")){
				exchange.getIn().setHeader("SuccessfulAuthorization", true);
			} else {
				exchange.getIn().setHeader("SuccessfulAuthorization", false);
			}

		}
	}
}
