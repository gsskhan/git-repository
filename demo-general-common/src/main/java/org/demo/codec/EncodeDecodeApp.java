package org.demo.codec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EncodeDecodeApp {
	
	private static Logger log = LoggerFactory.getLogger(EncodeDecodeApp.class);
	
	public static void main(String[] args) {
		
		String str = "GULAM SABIR SHAHBAZ KHAN";
		String encodedString = Base64.encodeBase64String(str.getBytes());
		
		log.info("Original = "+ str);
		log.info("Encoded = " + encodedString);
		log.info("Decoded = "+ new String(Base64.decodeBase64(encodedString)));
		log.info("end of program ...");
	}

}
