package com.ob.contentExtractor.parsers;


import java.io.File;
import java.io.IOException;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

public abstract class Parser {
	
	String fileName;
	
	public Parser(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String getCheckSum() {
		HashCode checkSumVal = null;
		try {
			checkSumVal = Files.asByteSource(new File(fileName)).hash(Hashing.sha512());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkSumVal.toString();
	}

	
	public static void main(String[] args)
	{
		//MessageDigest md = MessageDigest.getInstance("MD5");
		//try (InputStream is = Files.newInputStream(Paths.get("file.txt"));
	//	     DigestInputStream dis = new DigestInputStream(is, MessageDigest.getInstance("MD5"))) 
	//	{
	//	  /* Read decorated stream (dis) to EOF as normal... */
	//	}
	//	byte[] digest = md.digest();
		
	/**
	
		HashCode md5 = null;
		try {
			md5 = Files.asByteSource(new File("c:/users/obinnao2/gate.xml")).hash(Hashing.sha512());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Sy(md5.toString());
		System.out.println(md5.toString());
		*/
		/**
		URL aURL = null;
		try {
			aURL = new URL("c:/users/obinnao2/gate.xml");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   System.out.println("protocol = " + aURL.getProtocol());
	        System.out.println("authority = " + aURL.getAuthority());
	        System.out.println("host = " + aURL.getHost());
	        System.out.println("port = " + aURL.getPort());
	        System.out.println("path = " + aURL.getPath());
	        System.out.println("query = " + aURL.getQuery());
	        System.out.println("filename = " + aURL.getFile());
	        System.out.println("ref = " + aURL.getRef());
	    */  
		/**
	        Path file = new File("http://yahoo.co.uk").toPath();
	        boolean exists =java.nio.file.Files.exists(file);  
	        System.out.println(exists);
	        */
	}
}
