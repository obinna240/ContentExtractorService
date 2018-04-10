package com.ob.contentExtractor.parsers;

import java.util.Optional;

import com.ob.contentExtractorService.interfaces.ISimpleParser;
import com.ob.contentExtractorService.util.FileTypeChecker;

/**
 * 
 * @author oonyimadu
 *
 */
public class ParserFactory {
	public static ISimpleParser parse(Optional<String> parseObject)
	{
		int val = FileTypeChecker.isLocal(parseObject);
		if(val == 1) //local
		{
			
		}
		else if(val == 2) //online
		{
			
		}
		return null;
	}
}
