package com.ob.contentExtractor.parsers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.ob.contentExtractorService.interfaces.ISimpleParser;

/**
 * 
 * @author oonyimadu
 *
 */
public class DocParser extends Parser implements ISimpleParser
{
	
	
	public DocParser(String fileName)
	{
		super(fileName);
	}
	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getContentAsXml() {
		
		//public String parseToHTML() throws IOException, SAXException, TikaException {
		    ContentHandler handler = new ToXMLContentHandler();
		 
		    AutoDetectParser parser = new AutoDetectParser();
		    Metadata metadata = new Metadata();
		    try (InputStream stream = FileUtils.openInputStream(new File(getFileName()));) {
		        try {
					parser.parse(stream, handler, metadata);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TikaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return handler.toString();
		}
	
	
	@Override
	public String getContentAsString() {
		  
			BodyContentHandler handler = new BodyContentHandler();
		 
		    AutoDetectParser parser = new AutoDetectParser();
		    Metadata metadata = new Metadata();
		    try (InputStream stream = FileUtils.openInputStream(new File(getFileName()));) 
		    {
		        parser.parse(stream, handler, metadata);
		       //handler.toString()
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return handler.toString();
	}
	
	
	
	
}
