package com.ob.contentExtractorService.util;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author oonyimadu
 *
 */
public class FileTypeChecker {
	public static int isLocal(Optional<String> urlOrFileName)
	{
		String urlName = urlOrFileName.orElse("blank");
				
		if(urlName.equals("blank"))
		{
			return 4;
		}
		else if(urlName.indexOf("http") ==-1 ||urlName.indexOf("www") == -1)
		{
			int val;
			if( isFileExists(urlName))
			{
				val =  1;
			}
			return 1;
		}
		else if(urlName.indexOf("http") !=-1 ||urlName.indexOf("www") != -1)
		{
			return 2;
		}
		else
		{
			return 0;
		}
		
		
	}
	
	//if true, check if file exists
	public static boolean isFileExists(String file)
	{
		 Path path= new File(file).toPath();
	     boolean exists =java.nio.file.Files.exists(path);  
	     return exists;
	}
	
	public static void main(String[] args)
	{
		Optional<String> str = Optional.ofNullable("http://www.yahoo.co.uk").filter(s -> StringUtils.isNotBlank(s));
		int b = FileTypeChecker.isLocal(str);
		System.out.println(b);
		//if(FileTypeChecker.isLocal("c:/users/obinnao2/t1.txt")==true) {System.out.println("it exists");}
		//System.out.println(FileTypeChecker.isLocal("http://www.yahoo.co.uk"));
		//System.out.println(FileTypeChecker.isLocal("bbc.co.uk"));
		//System.out.println(FileTypeChecker.isLocal("c:/users/obinnao2/t1.txt"));
		//System.out.println(FileTypeChecker.isLocal(""));
	}
}
