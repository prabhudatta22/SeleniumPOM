package com.framework.testutils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"file:src\\test\\resources\\propertyFiles\\${environment}.properties"
})

public interface ConfigProperties extends Config{
	
	@Key("baseURI")
	String getBaseURI();
	
	@Key("basePath")
	String getBasePath();
	
	@Key("sheetName")
	String getSheetName();
	
	@Key("secretKey")
	String getSecretKey();
	
	@Key("invalidSecretKey")
	String getInvalidSecretKey();

}
